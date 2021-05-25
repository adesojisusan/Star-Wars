package tech.notzero.people.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.notzero.people.api.RestApi
import tech.notzero.people.api.ServiceBuilder
import tech.notzero.people.databinding.ActivityMainBinding
import tech.notzero.people.model.People
import tech.notzero.people.model.Result

class MainActivity : AppCompatActivity() {

    companion object{
        public val A_PEOPLE = "tech.notzero.people.model.People"
    }
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        makeApiCall()
    }

    private fun makeApiCall(){
        val request = ServiceBuilder.buildService(RestApi::class.java)
        val call = request.getPeople()

        call.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful){
                    updateUI(response.body()?.results ?: emptyList())
                }
            }
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateUI(peoples:List<People>){
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PeopleAdapter(this@MainActivity, peoples){
                toPeopleDetails(it)
            }
        }
    }

    private fun toPeopleDetails(people:People){
        val detailIntent = Intent(this, DetailActivity::class.java)
        detailIntent.putExtra(A_PEOPLE, people)
        startActivity(detailIntent)
    }
}
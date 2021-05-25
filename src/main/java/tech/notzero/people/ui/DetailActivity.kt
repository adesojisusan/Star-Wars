package tech.notzero.people.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import tech.notzero.people.R
import tech.notzero.people.databinding.ActivityDetailBinding
import tech.notzero.people.ext.getUser
import tech.notzero.people.model.People

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pData = intent.getParcelableExtra<People>(MainActivity.A_PEOPLE)
        val userData = pData?.getUser()

        Glide.with(this).load(loadRandomPhoto()).into(binding.image)
        binding.name.text = userData?.name
        binding.height.text = userData?.height
        binding.age.text = userData?.age
    }

    private fun loadRandomPhoto():String{
        val imageUrls = arrayListOf(
            "https://images.unsplash.com/photo-1451187580459-43490279c0fa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1352&q=80",
            "https://images.pexels.com/photos/920382/pexels-photo-920382.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
            "https://images.pexels.com/photos/356043/pexels-photo-356043.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        return imageUrls.random()
    }
}
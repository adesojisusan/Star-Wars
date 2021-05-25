package tech.notzero.people.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import tech.notzero.people.R
import tech.notzero.people.model.People

class PeopleAdapter(private val context:Context, private val peoples:List<People>, val block:(People)->Unit):RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.model_people_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(peoples[position])
    }

    override fun getItemCount(): Int {
        return peoples.size
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view), View.OnClickListener{
        private val mImage = view.findViewById<CircleImageView>(R.id.image)
        private val mName = view.findViewById<TextView>(R.id.name)
        private val mOther = view.findViewById<TextView>(R.id.other)
        private var people: People? = null

        init {
            view.setOnClickListener(this)
        }

        fun bind(people: People){
            this.people = people
            Glide.with(context).load(loadRandomPhoto()).into(mImage)
            mName.text = people.name
            mOther.text = people.gender
        }

        override fun onClick(v: View?) {
            val safePeople = people ?: return
            block(safePeople)
        }
    }

    private fun loadRandomPhoto():String{
        val imageUrls = arrayListOf("https://images.unsplash.com/photo-1451187580459-43490279c0fa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1352&q=80",
            "https://images.pexels.com/photos/920382/pexels-photo-920382.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
            "https://images.pexels.com/photos/356043/pexels-photo-356043.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        return imageUrls.random()
    }
}
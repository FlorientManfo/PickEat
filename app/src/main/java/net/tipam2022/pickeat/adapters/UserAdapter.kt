package net.tipam2022.pickeat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import net.tipam2022.pickeat.R
import net.tipam2022.pickeat.entities.UserModel

class UserAdapter(private val users: ArrayList<UserModel>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        lateinit var photo: CircleImageView
        lateinit var name: TextView
        init{
            photo = view.findViewById<CircleImageView>(R.id.profile)
            name = view.findViewById<TextView>(R.id.profile_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.circlefriend, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.photo.setImageResource(users[position].photo)
        holder.name.text = users[position].name
    }

    override fun getItemCount(): Int {
        return users.count()
    }
}
package net.tipam2022.pickeat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.tipam2022.pickeat.R
import net.tipam2022.pickeat.entities.Meal
import net.tipam2022.pickeat.entities.Publication


class MealAdapter(private val meals : ArrayList<Meal>) : RecyclerView.Adapter<MealAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.publication_item, parent, false)
        return MyViewHolder (view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.category.text = meals[position].categoryName
        holder.name.text = meals[position].name
        holder.description.text = meals[position].description
        holder.photo.setImageResource(meals[position].Image)
    }

    override fun getItemCount (): Int {
        return meals.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var category: TextView = itemView.findViewById<View>(R.id.category) as TextView
        internal var name: TextView = itemView.findViewById(R.id.meal) as TextView
        internal var description: TextView = itemView.findViewById(R.id.description) as TextView
        internal var photo: ImageView = itemView.findViewById(R.id.meal_photo)
    }
}
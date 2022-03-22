package net.tipam2022.pickeat.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.tipam2022.pickeat.R
import net.tipam2022.pickeat.entities.Publication


class PublicationAdapter(private val publications : ArrayList<Publication>) : RecyclerView.Adapter<PublicationAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.publication_item, parent, false)
        return MyViewHolder (view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.category.text = publications[position].meal.categoryName
        holder.meal.text = publications[position].meal.name
        holder.price.text = publications[position].price.toString()
        holder.description.text = publications[position].description
    }

    override fun getItemCount (): Int {
        return publications.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //internal lateinit var photo: ImageView
        internal  lateinit var category: TextView
        internal lateinit var meal: TextView
        internal lateinit var price: TextView
        internal lateinit var description: TextView
        internal lateinit var notation: RatingBar

        init {
            category = itemView.findViewById<View>(R.id.category) as TextView
            meal = itemView.findViewById<View>(R.id.meal) as TextView
            price = itemView.findViewById<View>(R.id.price) as TextView
            description = itemView.findViewById<View>(R.id.description) as TextView
        }
    }
}
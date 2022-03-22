package net.tipam2022.pickeat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.tipam2022.pickeat.R
import net.tipam2022.pickeat.entities.CategoryModel


class CategoryAdapter(private val categoryModels : ArrayList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_layout, parent, false)
        return MyViewHolder (view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = categoryModels[position].title
        holder.background.setImageResource(categoryModels[position].background)
    }

    override fun getItemCount (): Int {
        return categoryModels.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var name: TextView
        lateinit  var background: ImageView

        init{
            name = itemView.findViewById<TextView>(R.id.category_name)
            background = itemView.findViewById<ImageView>(R.id.background_category)
        }
    }
}
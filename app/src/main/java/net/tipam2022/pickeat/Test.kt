package net.tipam2022.pickeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import net.tipam2022.pickeat.adapters.CategoryAdapter
import net.tipam2022.pickeat.databinding.ActivityTestBinding
import net.tipam2022.pickeat.databinding.FragmentCategoryBinding
import net.tipam2022.pickeat.entities.CategoryModel


class Test : AppCompatActivity() {


    var categories = arrayListOf<CategoryModel>(
        CategoryModel("Cameroonian", R.drawable.category1),
        CategoryModel("Traditional Cameroonian", R.drawable.category1),
        CategoryModel("Fast-Fod", R.drawable.category1),
        CategoryModel("Chinese Meal", R.drawable.category1),
        CategoryModel("European Meal", R.drawable.category1),
        CategoryModel("English Meal", R.drawable.category1),
        CategoryModel("African Meal", R.drawable.category1),
        CategoryModel("Indian Meal", R.drawable.category1),
        CategoryModel("Drink", R.drawable.category1),
        CategoryModel("Other meal", R.drawable.category1),
    )
    lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.categoryList
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = CategoryAdapter(categories)
        println(CategoryAdapter(categories).itemCount)
    }
}
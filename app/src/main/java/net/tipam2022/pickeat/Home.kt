package net.tipam2022.pickeat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import net.tipam2022.pickeat.adapters.CategoryAdapter
import net.tipam2022.pickeat.adapters.CategoryAdapter2
import net.tipam2022.pickeat.adapters.MenuAdapter
import net.tipam2022.pickeat.databinding.FragmentHomeBinding
import net.tipam2022.pickeat.entities.CategoryModel
import net.tipam2022.pickeat.entities.MealModel
import net.tipam2022.pickeat.entities.MenuModel
import net.tipam2022.pickeat.entities.PublicationModel
import java.io.LineNumberReader


class Home : Fragment() ,View.OnClickListener{

    lateinit var binding: FragmentHomeBinding
    private var categories = arrayListOf<CategoryModel>(
        CategoryModel("Cameroonian", R.drawable.category_1),
        CategoryModel("Traditional Cameroonian", R.drawable.category_2),
        CategoryModel("Fast-Fod", R.drawable.category_3),
        CategoryModel("Asiatic Meal", R.drawable.category_4),
        CategoryModel("European Meal", R.drawable.category_5),
        CategoryModel("English Meal", R.drawable.category_6),
        CategoryModel("Drink", R.drawable.category_7),
        CategoryModel("Cake", R.drawable.category_8)
    )


    var publications = arrayListOf<PublicationModel>(
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.", R.drawable.menu1), "Delicious Eru with Garry", R.drawable.menu1, 0.0, 2500.0),
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.", R.drawable.menu1), "Delicious Eru with Garry", R.drawable.menu1, 0.0, 2500.0),
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.", R.drawable.menu1), "Delicious Eru with Garry", R.drawable.menu1, 5.0, 2500.0)
    )
    var menus = arrayListOf<MenuModel>(
        MenuModel("ERU", "test", "test", "test", R.drawable.menu1, publications)
        , MenuModel("KOKI", "test", "test", "test", R.drawable.menu1, publications)
        , MenuModel("Pommes", "test", "test", "test", R.drawable.pomme, publications)
        , MenuModel("Taro", "test", "test", "test", R.drawable.taro, publications)
        , MenuModel("Riz", "test", "test", "test", R.drawable.riz, publications)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.category.setOnClickListener(this)
        //Go to search page
        var topmenu = binding.topMenu.menu
        topmenu.children.elementAt(0).setOnMenuItemClickListener{ search() }


        var mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.categories!!.layoutManager = mLayoutManager
        val mAdapter = CategoryAdapter2(categories){position ->  categoryClickListener(position)}
        binding.categories!!.adapter = mAdapter
        println(mAdapter.itemCount)
        binding.recyclerView!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = MenuAdapter(menus)

        //Action on searching from home searchView


        return binding.root
    }

    //Find with searchView
    override fun onClick(p0: View?) {
        when(p0){
            binding.category->{
                var fragment = Category()
                loadFragment(fragment)
            }
        }
    }

    //See all the categories
    private fun categoryClickListener(position: Int) {
        var fragment = Menu()
        var selectedCategory = categories[position]
        var arguments = Bundle()
        arguments.putString("title", selectedCategory.title)
        fragment.arguments = arguments
        loadFragment(fragment)
    }

    //Switch to another fragment
    private fun loadFragment(fragment: Fragment) {
        var transaction = activity?.supportFragmentManager
        transaction?.beginTransaction()
        ?.replace(R.id.container, fragment)
        ?.commit()
    }

    private fun search(): Boolean {
        var transaction = requireFragmentManager().beginTransaction()
        var fragment = Menu()
        fragment.arguments = arguments
        loadFragment(fragment)
        return true
    }


}
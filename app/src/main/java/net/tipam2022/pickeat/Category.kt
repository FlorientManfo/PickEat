package net.tipam2022.pickeat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.tipam2022.pickeat.adapters.CategoryAdapter
import net.tipam2022.pickeat.adapters.MenuAdapter
import net.tipam2022.pickeat.databinding.FragmentCategoryBinding
import net.tipam2022.pickeat.entities.CategoryModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Category.newInstance] factory method to
 * create an instance of this fragment.
 */
class Category : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var isSearching: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
    lateinit var binding: FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        val recycleView = binding.categoryList
        var mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycleView!!.layoutManager = mLayoutManager
        val mAdapter = CategoryAdapter(categories){position ->  categoryClickListener(position)}
        recycleView!!.adapter = mAdapter
        println(mAdapter.itemCount)

        var navigationIcon = binding.toolbar.setNavigationOnClickListener {
            var home = Home()
            loadFragment(home)
        }

        return binding.root
    }

    private fun categoryClickListener(position: Int){
        var fragment = Menu()
        var selectedCategory = categories[position]
        var arguments = Bundle()
        arguments.putString("title", selectedCategory.title)
        fragment.arguments = arguments
        activity?.supportFragmentManager
            ?.beginTransaction()?.replace(R.id.container, fragment)
            ?.commit()
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        var transaction = activity?.supportFragmentManager
        transaction?.beginTransaction()
            ?.replace(R.id.container, fragment)
            ?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
            ?.addToBackStack("menu")
            ?.commit()
    }
}
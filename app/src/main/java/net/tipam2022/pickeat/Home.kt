package net.tipam2022.pickeat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import net.tipam2022.pickeat.adapters.CategoryAdapter
import net.tipam2022.pickeat.adapters.CategoryAdapter2
import net.tipam2022.pickeat.databinding.FragmentHomeBinding
import net.tipam2022.pickeat.entities.CategoryModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() ,View.OnClickListener{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentHomeBinding
    private var categories = arrayListOf<CategoryModel>(
        CategoryModel("Cameroonian", R.drawable.category1),
        CategoryModel("Traditional Cameroonian", R.drawable.category1),
        CategoryModel("Fast-Fod", R.drawable.category1),
        CategoryModel("Chinese Meal", R.drawable.category1),
        CategoryModel("European Meal", R.drawable.category1),
        CategoryModel("English Meal", R.drawable.category1),
        CategoryModel("African Meal", R.drawable.category1),
        CategoryModel("Indian Meal", R.drawable.category1),
        CategoryModel("Drink", R.drawable.category1),
        CategoryModel("Other meal", R.drawable.category1)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.category.setOnClickListener(this)
        //val controller = AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.animation_scale)
        //binding.categories.layoutAnimation = controller
        //binding.categories.scheduleLayoutAnimation()


        var mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.categories!!.layoutManager = mLayoutManager
        val mAdapter = CategoryAdapter2(categories){position ->  categoryClickListener(position)}
        binding.categories!!.adapter = mAdapter
        println(mAdapter.itemCount)

        return binding.root
    }

    override fun onClick(p0: View?) {
        when(p0){
            binding.category->{
                var fragment = Category()
                loadFragment(fragment)
            }
            binding.homeSearch->{
                var fragment = Category()
                var arguments = Bundle()
                arguments.putBoolean("isSearching", true)
                fragment.arguments = arguments
                loadFragment(fragment)
            }
        }
    }

    private fun categoryClickListener(position: Int){
        var fragment = Menu()
        var selectedCategory = categories[position]
        var arguments = Bundle()
        arguments.putString("title", selectedCategory.title)
        fragment.arguments = arguments
        loadFragment(fragment)

        //Toast.makeText(activity?.baseContext, categories[position].title, Toast.LENGTH_SHORT).show()
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        var transaction = activity?.supportFragmentManager
        transaction?.beginTransaction()
        ?.replace(R.id.container, fragment)
        ?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
        ?.commit()
    }
}
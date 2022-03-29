package net.tipam2022.pickeat

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.view.menu.MenuBuilder
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import net.tipam2022.pickeat.adapters.PublicationAdapter
import net.tipam2022.pickeat.databinding.FragmentAccountBinding
import net.tipam2022.pickeat.entities.MealModel
import net.tipam2022.pickeat.entities.PublicationModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Account.newInstance] factory method to
 * create an instance of this fragment.
 */
class Account : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //Test Values
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onOptionsMenuClosed(menu: Menu) {
        super.onOptionsMenuClosed(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return super.onOptionsItemSelected(item)
    }


    var publications = arrayListOf<PublicationModel>(
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.", R.drawable.menu1), "Delicious Eru with Garry", R.drawable.menu1, 5.0, 2500.0),
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.", R.drawable.menu1), "Delicious Eru with Garry", R.drawable.menu1, 5.0, 2500.0),
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.", R.drawable.menu1), "Delicious Eru with Garry", R.drawable.menu1, 5.0, 2500.0)
    )

    lateinit var binding: FragmentAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false)

        val recycleView = binding.publicationList
        var mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycleView!!.layoutManager = mLayoutManager
        val mAdapter = PublicationAdapter(publications)
        recycleView!!.adapter = mAdapter
        println(mAdapter.itemCount)

        val toAppBarr = binding.topAppBar
        toAppBarr.setOnMenuItemClickListener{menuItemListener(it)}
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (menu is MenuBuilder) (menu as MenuBuilder).setOptionalIconsVisible(true)
        inflater.inflate(R.menu.profile_option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Account.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Account().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun menuItemListener(item: MenuItem): Boolean{
        return when (item.itemId) {
            R.id.logout -> {
                auth = FirebaseAuth.getInstance()
                // navigate to settings screen
                if (auth.currentUser != null) {
                    auth.signOut()
                    startActivity(Intent(activity, Login::class.java))
                }
                Toast.makeText(activity, "${auth.currentUser}", Toast.LENGTH_SHORT).show()
                println("Logout!")
                true
            }
            R.id.edit -> {
                // save profile changes
                Toast.makeText(activity, "Edit user!", Toast.LENGTH_SHORT).show()
                println("Edit!")
                true
            }
            else -> false
        }
    }
}
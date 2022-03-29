package net.tipam2022.pickeat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.chip.Chip
import net.tipam2022.pickeat.adapters.MenuAdapter
import net.tipam2022.pickeat.databinding.FragmentMenuBinding
import net.tipam2022.pickeat.entities.MealModel
import net.tipam2022.pickeat.entities.MenuModel
import net.tipam2022.pickeat.entities.PublicationModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Menu2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Menu : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    lateinit var binding: FragmentMenuBinding

    var publications = arrayListOf<PublicationModel>(
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.", R.drawable.menu1), "Delicious Eru with Garry", R.drawable.menu1, 0.0, 2500.0),
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.", R.drawable.menu1), "Delicious Eru with Garry", R.drawable.menu1, 0.0, 2500.0),
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.", R.drawable.menu1), "Delicious Eru with Garry", R.drawable.menu1, 5.0, 2500.0)
    )

    var menus = arrayListOf<MenuModel>(
        MenuModel("test", "test", "test", "test", R.drawable.menu1, publications)
        , MenuModel("test", "test", "test", "test", R.drawable.menu1, publications)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        val recycleView = binding.menuList
        val mAdapter = MenuAdapter(menus)
        recycleView.adapter = mAdapter
        println(mAdapter.itemCount)

        /*var filter = binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId)
            {
                val mDialogBox = LayoutInflater.from(this).inflate(R.layout.activity_filterpopup, null)
                val mBuilder = AlertDialog.Builder(this)
                mBuilder.setView(mDialogBox)

                val mAlertDialog = mBuilder.show()

                mDialogBox.findViewById<Button>(R.id.submit).setOnClickListener {
                    val ville1 = mDialogBox.findViewById<Chip>(R.id.ville2)

                    val region1 = mDialogBox.findViewById<Chip>(R.id.region2)
                    val region2 = mDialogBox.findViewById<Chip>(R.id.region3)
                    val region3 = mDialogBox.findViewById<Chip>(R.id.region4)
                    val region4 = mDialogBox.findViewById<Chip>(R.id.region5)
                    val region5 = mDialogBox.findViewById<Chip>(R.id.region6)
                    val region6 = mDialogBox.findViewById<Chip>(R.id.region7)
                    val region7 = mDialogBox.findViewById<Chip>(R.id.region8)

                    val list = arrayListOf<String>()
                    val montant = mDialogBox.findViewById<EditText>(R.id.editText)




                }

                mDialogBox.findViewById<Button>(R.id.cancel).setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
        }*/
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Menu2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                Menu().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
package net.tipam2022.pickeat

import android.os.Bundle
import android.view.*
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import net.tipam2022.pickeat.databinding.FragmentAccountBinding
import net.tipam2022.pickeat.databinding.FragmentMenuBinding



class Menu : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentMenuBinding
    lateinit var chipOptions: ChipGroup
    lateinit var shearchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding  = FragmentMenuBinding.inflate(inflater, container, false)






        chipOptions = binding.filter.chipParent
        handleSelection()
        chipOptions.children.forEach {
            (it as Chip).setOnCheckedChangeListener { buttonView, isChecked ->
                handleSelection()
                println("ok")
            }
        }
        println(chipOptions.childCount)
        return binding.root
    }

    private fun handleSelection() {
        var text = "Checked Chips : "
        chipOptions.checkedChipIds.forEach{
            val chip = binding.root.findViewById<Chip>(it)
            text +=("\n${ chip.text}")
        }
        println(text)
        Toast.makeText(activity, "$text", Toast.LENGTH_SHORT*7).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

}
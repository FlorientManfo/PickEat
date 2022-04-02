package net.tipam2022.pickeat

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import net.tipam2022.pickeat.databinding.FragmentPublishBinding
import net.tipam2022.pickeat.databinding.LayoutEditBinding
import java.io.File
import java.io.PrintWriter

lateinit var binding: FragmentPublishBinding
class Publish : Fragment() {

    private final val fileName = "PhoneNumber.txt"
    private final val folderName = "PickEat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPublishBinding.inflate(inflater, container, false)
        var button: Button = binding.button2
        button.setOnClickListener {

        }
        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == RC_STORAGE_WRITE_PERMS){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
        }
    }

    private fun checkWriteExternalStoragePermission(): Boolean {
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
            PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), Array<String>(1){ Manifest.permission.WRITE_EXTERNAL_STORAGE },RC_STORAGE_WRITE_PERMS)
            return true
        }
        return false
    }
}
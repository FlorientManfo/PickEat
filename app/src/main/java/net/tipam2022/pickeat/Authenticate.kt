package net.tipam2022.pickeat

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import net.tipam2022.pickeat.databinding.ActivityAuthenticateBinding
import net.tipam2022.pickeat.databinding.ActivityLoginBinding
import net.tipam2022.pickeat.upload.UploadUser
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter

class Authenticate: AppCompatActivity() {


    lateinit var binding: ActivityAuthenticateBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        mStorageRef = FirebaseStorage.getInstance().getReference("users")
        mODatabaseRef = FirebaseDatabase.getInstance().getReference("users")

        val storedVerificationId = intent.getStringExtra("storedVerificationId")
        val verify = binding.confirmBtn
        val otpGiven = binding.otp

        //Save the current phone number for futures usages
        val file = File("phone")

        verify.setOnClickListener{
            val otp = otpGiven.text.toString().trim()
            if(otp.isNotEmpty()){
                val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    storedVerificationId.toString(), otp)
                signInWithPhoneAuthCredential(credential)


            }
            else{
                Toast.makeText(this, "Enter OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(applicationContext, Welcome::class.java))

                    uploadUser()

                    //var file = File("PhoneNumber.txt")

                    finish()
                    println("ok")
                } else {

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show()
                    }
                }
            }


    }

    private fun uploadUser(){
        var user = UploadUser(currentPhone)
        mODatabaseRef.child(currentPhone).setValue(user)
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
        if(ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, Array<String>(1){WRITE_EXTERNAL_STORAGE},RC_STORAGE_WRITE_PERMS)
            return true
        }
        return false
    }
}
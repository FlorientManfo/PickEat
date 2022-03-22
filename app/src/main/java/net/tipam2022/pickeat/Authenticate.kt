package net.tipam2022.pickeat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import net.tipam2022.pickeat.databinding.ActivityAuthenticateBinding
import net.tipam2022.pickeat.databinding.ActivityLoginBinding

class Authenticate: AppCompatActivity() {
    lateinit var binding: ActivityAuthenticateBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        val storedVerificationId = intent.getStringExtra("storedVerificationId")
        val verify = binding.confirmBtn
        val otpGiven = binding.otp

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
                    finish()
                    println("ok")
                } else {

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}
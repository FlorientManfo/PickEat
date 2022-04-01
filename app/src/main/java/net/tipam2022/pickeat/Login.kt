package net.tipam2022.pickeat

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import net.tipam2022.pickeat.databinding.ActivityLoginBinding
import java.util.concurrent.TimeUnit

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private val pattern = Regex("[6][7,5,8,9]\\d{7}")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        val login = binding.loginBtn

        val currentUser = auth.currentUser
        if(currentUser != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

        login.setOnClickListener{
            login()
        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                startActivity(Intent(applicationContext, Home::class.java))
                finish()
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                Log.d("TAG", "onCodeSent:$verificationId")
                storedVerificationId = verificationId
                resendToken = token

                val intent = Intent(applicationContext, Authenticate::class.java)
                intent.putExtra("storedVerificationId", storedVerificationId)
                startActivity(intent)
            }
        }
    }

    private fun login(){
        val mobileNumber = binding.phoneNumber
        var number = mobileNumber.text.toString().trim()
        println("$number")
        currentPhone = number
        if(!number.matches(pattern)){

            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_LONG*2).show()
        }
        else if(number.isNotEmpty()){
            number = "+237$number"
            sendVerificationCode(number)
        }
        else{
            Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendVerificationCode(phoneNumber: String){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}
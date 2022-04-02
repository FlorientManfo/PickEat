package net.tipam2022.pickeat

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ScrollView
import android.widget.Toast
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import net.tipam2022.pickeat.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        var currentUer = auth.currentUser

        var file = File("PhoneNumber.txt")
        currentPhone = readOnFile(this, file)

        if (currentUer == null) {
            startActivity(Intent(applicationContext, Login::class.java))
            finish()
        }
        else{
            title=resources.getString(R.string.title_home)
            loadFragment(Home())
        }

        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_favorite-> {
                    title=resources.getString(R.string.title_favourites)
                    loadFragment(Favorite())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_home-> {
                    title=resources.getString(R.string.title_home)
                    loadFragment(Home())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_publish-> {
                    title=resources.getString(R.string.title_publish)
                    loadFragment(Publish())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_notification-> {
                    title=resources.getString(R.string.title_notification)
                    loadFragment(Notification())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_account-> {
                    title=resources.getString(R.string.title_account)
                    loadFragment(Account())
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false

        }



        Firebase.messaging.subscribeToTopic("New Meal")
            .addOnCompleteListener { task ->
                var msg = getString(R.string.msg_subscribed)
                if (!task.isSuccessful) {
                    msg = getString(R.string.msg_subscribe_failed)
                }
                Log.d(TAG, msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }

        /*binding.category.setOnClickListener{
            loadFragment(Menu())
        }*/
    }


    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
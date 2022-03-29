package net.tipam2022.pickeat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import net.tipam2022.pickeat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        var currentUer = auth.currentUser


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

        binding.category.setOnClickListener{
            loadFragment(Menu())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
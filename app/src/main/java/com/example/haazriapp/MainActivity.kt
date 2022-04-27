package com.example.haazriapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.haazriapp.fragments.Front_Fragment
import com.example.haazriapp.fragments.moreOptionFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val front_Fragment = Front_Fragment()
    private val registerfragment = moreOptionFragment()
    private lateinit var navController: NavController
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        replaceFragment(front_Fragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.Home_activity -> {
                    replaceFragment(front_Fragment)
                }
                R.id.more -> {
                    replaceFragment(registerfragment)
                }
            }

            true
        }

    }

    private fun checkUser() {
        val firebaseuser = firebaseAuth.currentUser

        if (firebaseuser != null) {

//            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }
    }
}

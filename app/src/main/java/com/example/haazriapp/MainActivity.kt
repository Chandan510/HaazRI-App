package com.example.haazriapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.haazriapp.fragments.Front_Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val front_Fragment = Front_Fragment()
    lateinit var navController: NavController
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragment_container)

        bottom_navigation.setupWithNavController(navController)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()




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



    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}

package com.example.haazriapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.haazriapp.ModelDataClass.Users
import com.example.haazriapp.databinding.ActivityRegistrationBinding
import com.example.haazriapp.fragments.Front_Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegistrationBinding
    private lateinit var firebaseAuth:FirebaseAuth
    private var email = ""
    private var password = ""
    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)

        setContentView(binding.root)




        firebaseAuth = FirebaseAuth.getInstance()

        val currentUser = firebaseAuth.currentUser

        binding.alreadyhaveaccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.registrationbtn.setOnClickListener {


            email = binding.emailregistration.text.toString()
            password = binding.passwordregistration.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.emailregistration.error = "Invalid Email"
            }
            else if(TextUtils.isEmpty(password)){
                binding.passwordregistration.error = "Please Enter Password"
            }
            else if (password.length < 6){
                binding.passwordregistration.error = "Password must atleast 6 character long"
            }
            else{
                signup()
            }

        }

    }

    private fun signup() {



        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
            .addOnFailureListener { e->
                Toast.makeText(this, "Registration Failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }










    }


}
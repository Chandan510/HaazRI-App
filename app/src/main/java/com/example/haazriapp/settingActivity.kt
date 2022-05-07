package com.example.haazriapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.haazriapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_setting.*


lateinit var binding: FragmentRegisterBinding
lateinit var firebaseAuth: FirebaseAuth

class settingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

//        val currentUser = firebaseAuth.currentUser
//
//        logoutbtn.setOnClickListener {
//            firebaseAuth.signOut()
//            val intent = Intent (this, RegistrationActivity::class.java)
//            RegistrationActivity().startActivity(intent)
//
//        }
//
//        var currentEmail = currentUser!!.email
//        val index = currentEmail!!.indexOf('@')
//        currentEmail = currentEmail.substring(0,index)
//        nameText.text = currentEmail.toString()
//
//        vectornamelogo.text = currentEmail[0].toString()

    }
}
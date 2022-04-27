package com.example.haazriapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.haazriapp.LoginActivity
import com.example.haazriapp.R
import com.example.haazriapp.RegistrationActivity
import com.example.haazriapp.databinding.ActivityLoginBinding
import com.example.haazriapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*


class moreOptionFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(layoutInflater,container,false)
        firebaseAuth = FirebaseAuth.getInstance()

        val currentUser = firebaseAuth.currentUser

        binding.logoutbtn.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent (getActivity(), RegistrationActivity::class.java)
            requireActivity().startActivity(intent)

        }

        var currentEmail = currentUser!!.email
        val index = currentEmail!!.indexOf('@')
        currentEmail = currentEmail.substring(0,index)
        binding.nameText.text = currentEmail.toString()

        binding.vectornamelogo.text = currentEmail[0].toString()

        return binding.root
    }


}
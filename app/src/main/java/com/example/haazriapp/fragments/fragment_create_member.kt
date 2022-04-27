package com.example.haazriapp.fragments

import android.os.Bundle

import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.haazriapp.ModelDataClass.Users
import com.example.haazriapp.R
import com.example.haazriapp.databinding.FragmentCreateMemberBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_create_member.*


class fragment_create_member : Fragment() {

    lateinit var binding: FragmentCreateMemberBinding
    lateinit var db: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateMemberBinding.inflate(layoutInflater, container, false)

        binding.addmemberbtn.setOnClickListener {

            val name = edttnameText.text.toString()
            val mobile = mobileedttext.text.toString()


            checkValue(name, mobile)


        }



        return binding.root
    }

    private fun checkValue(name:String, mobile:String) {


        if(TextUtils.isEmpty(mobile)){
            binding.mobileedttext.error = "Enter Mobile"
        }
        else if (TextUtils.isEmpty(name)){
            binding.edttnameText.error = "Enter Name"
        }
        else if (mobile.length < 10){
            binding.mobileedttext.error = "Number must be of 10 digit"
        }
        else{
            firebaseAuth = FirebaseAuth.getInstance()
            val currentuser = firebaseAuth.currentUser

            db = FirebaseDatabase.getInstance().getReference(currentuser!!.uid).child("Members")

            val users = Users(name, mobile)

            db.child(name).setValue(users)

                .addOnSuccessListener {
                    val fragment = Front_Fragment()
                    val transaction = fragmentManager?.beginTransaction()
                    transaction?.replace(R.id.fragment_container, fragment)
                    transaction?.commit()

                }.addOnCanceledListener {
                }
        }
    }


}
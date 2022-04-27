package com.example.haazriapp.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haazriapp.ModelDataClass.Users
import com.example.haazriapp.R
import com.example.haazriapp.databinding.FragmentFrontBinding
import com.example.haazriapp.haazri_Adapter.front_adapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Front_Fragment : Fragment(){
    lateinit var binding: FragmentFrontBinding
    lateinit var database:DatabaseReference
    lateinit var membersArrayList: ArrayList<Users>
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var progressBar:ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFrontBinding.inflate(layoutInflater, container, false)

        progressBar = ProgressDialog(requireContext())
        progressBar.setTitle("Please Wait")
        progressBar.setMessage("Logging In...")
        progressBar.setCanceledOnTouchOutside(false)

        membersArrayList = arrayListOf<Users>()

        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser.toString()

        binding.recyclerviewfront001.layoutManager = LinearLayoutManager(requireContext())

        val sdf = SimpleDateFormat("dd")
        val currentDate = sdf.format(Date())

        val mm = SimpleDateFormat("MMMM")
        val currentMonth = mm.format(Date())

        val dd = SimpleDateFormat("EEE")
        val currentDay = dd.format(Date())

        val current_day = currentDate + "-" + currentMonth + " | " + currentDay

        binding.currentdatetext.text = current_day

        membersArrayList.clear()
        getMembersData()

        binding.addmemberbtnfront.setOnClickListener {
            val fragment = fragment_create_member()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container,fragment)?.commit()
//            val action = Front_FragmentDirections.actionFrontFragmentToFragmentCreateMember()
//            Navigation.findNavController(it).navigate(action)
        }

        return binding.root
    }



    private fun getMembersData() {

        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        database = FirebaseDatabase.getInstance().getReference(currentUser!!.uid).child("Members")
        database.addValueEventListener(object : ValueEventListener
             {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(userSnapShot in snapshot.children){
                        val users = userSnapShot.getValue(Users::class.java)
                        membersArrayList.add(users!!)
                        binding.noOfMembers.text = membersArrayList.size.toString()
                        Log.e("@@@@@",  membersArrayList.toString())
                    }
                    binding.recyclerviewfront001.adapter = front_adapter(membersArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}
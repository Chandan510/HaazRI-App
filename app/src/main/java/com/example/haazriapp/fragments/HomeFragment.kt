package com.example.haazriapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.haazriapp.R
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haazriapp.ModelDataClass.Users
import com.example.haazriapp.ModelDataClass.all_data
import com.example.haazriapp.ModelDataClass.haazRiTime
import com.example.haazriapp.ModelDataClass.newAttendence
import com.example.haazriapp.databinding.FragmentHomeBinding
import com.example.haazriapp.haazri_Adapter.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase.getInstance

import kotlinx.android.synthetic.main.fragment_home.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var database:DatabaseReference
    lateinit var dataArrayList:ArrayList<all_data>
    lateinit var firebaseAuth: FirebaseAuth
    private var fullTime = 0
    private var HalfTime = 0
    private var OverTime = 0
    private var holidayTime = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        dataArrayList = arrayListOf<all_data>()

        var name:String = ""

        binding.homerecyclerview.layoutManager = LinearLayoutManager(requireContext())

        arguments?.let {
            val args = HomeFragmentArgs.fromBundle(it)
            binding.nameText.text = args.perosnName
            binding.mobileText.text = args.mobile
            binding.memberImage01.text = args.perosnName[0].toString()
            getdata(args.perosnName)

        }


        return binding.root
    }

    private fun getdata(args:String) {

            firebaseAuth = FirebaseAuth.getInstance()
            val currentUser = firebaseAuth.currentUser

            database = FirebaseDatabase.getInstance().getReference(currentUser!!.uid).child("Members").child(args)
            database.child("data").addValueEventListener(object : ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        for(userSnapShot in snapshot.children){
                            val time = userSnapShot.getValue()
                            val date = userSnapShot.key
                            val us = all_data(date, time.toString())

                            dataArrayList.add(us)
                            Log.e("date", us.date.toString())
                            if (time=="1"){
                                fullTime+=1
                            }
                            else if (time=="2"){
                                HalfTime += 1
                            }
                            else if (time == "3"){
                                OverTime += 1
                            }
                            else if (time == "40"){
                                holidayTime += 1
                            }
                        }

                        binding.totalwokingdayshome.text = dataArrayList.size.toString()
                        binding.fulltimehome.text = fullTime.toString()
                        binding.halftimehome.text = HalfTime.toString()
                        binding.overtimehome.text = OverTime.toString()
                        binding.holidayhome.text = holidayTime.toString()

                        binding.homerecyclerview.adapter = haazriAdapter(dataArrayList)

                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        }
    }


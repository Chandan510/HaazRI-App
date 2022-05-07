package com.example.haazriapp.haazri_Adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController

import androidx.navigation.Navigation

import androidx.recyclerview.widget.RecyclerView
import com.example.haazriapp.R
import com.example.haazriapp.ModelDataClass.Users
import com.example.haazriapp.ModelDataClass.all_data
import com.example.haazriapp.RegistrationActivity
import com.example.haazriapp.fragments.Front_FragmentDirections
import com.example.haazriapp.fragments.fragment_create_member
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

import com.google.firebase.auth.FirebaseAuth


class front_adapter(var userArrayList: ArrayList<Users>) :
    RecyclerView.Adapter<front_adapter.front_viewHolder>() {
    lateinit var database: DatabaseReference

    lateinit var firebaseAuth: FirebaseAuth
    private  var time: String="0"

    lateinit var timearraylist: ArrayList<all_data>

    var tm: String = "0"
    val sdf = SimpleDateFormat("dd")
    val currentDate = sdf.format(Date())

    val mm = SimpleDateFormat("MMMM")
    val currentMonth = mm.format(Date())

    val dd = SimpleDateFormat("EEE")
    val currentDay = dd.format(Date())

    val YY = SimpleDateFormat("yy")
    val current_year = dd.format(Date())


    val current_day = currentDate + "-" + currentMonth + " | " + currentDay


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): front_viewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.rcv_front, parent, false)
        return front_viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: front_viewHolder, position: Int) {



        firebaseAuth = FirebaseAuth.getInstance()
        val person = userArrayList[position].name.toString()
        val mobile = userArrayList[position].mobile.toString()

        timearraylist = arrayListOf<all_data>()

        holder.nametxt.text = userArrayList[position].name
        holder.mobiletxt.text = userArrayList[position].mobile

        val contact = person[0]


        holder.logotext.text = contact.toString()

        holder.deletebtn.setOnClickListener {

            val builder: AlertDialog.Builder = AlertDialog.Builder(it.context)
            builder.setTitle("Delete Members")
            val view =  LayoutInflater.from(it.context).inflate(R.layout.dialogedeletemember,null)
            val username = view.findViewById<EditText>(R.id.useremaildialoge)
            builder.setView(view)
            builder.setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->
                database =
                    FirebaseDatabase.getInstance().getReference(firebaseAuth.uid!!).child("Members")
                database.child(person).removeValue()

                val action01 = Front_FragmentDirections.actionFrontFragmentSelf()
                Navigation.findNavController(it).navigate(action01)

            })
            builder.setNegativeButton("close",  DialogInterface.OnClickListener { dialogInterface, i ->  })

            builder.show()

        }

        holder.fulltimebtn.setOnClickListener {
            time = "1"
            savedata(person)
            userArrayList.clear()
        }
        holder.halftimebtn.setOnClickListener {
            time = "2"
            savedata(person)
            userArrayList.clear()
        }
        holder.overtimebtn.setOnClickListener {
            time = "3"
            savedata(person)
            userArrayList.clear()
        }
        holder.holidaybtn.setOnClickListener {
            time = "4"
            savedata(person)
            userArrayList.clear()
        }

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = sdf.format(Date())

        val currentUser01 = firebaseAuth.currentUser
        database = FirebaseDatabase.getInstance().getReference(currentUser01!!.uid).child("Members")
            .child(person)
        database.child("data").get().addOnSuccessListener {
            if (it.exists()) {
                tm = it.child(currentDate).value.toString()
                Log.e("tmm", it.value.toString())
                when (tm) {
                    "1" -> {
                        holder.fulltimebtn.setBackgroundResource(R.drawable.timebackground)
                        holder.halftimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                        holder.overtimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                        holder.holidaybtn.setBackgroundResource(R.drawable.selecttimebackground)
                        holder.fulltimebtn.setTextColor(Color.parseColor("#ffffff"))
                    }

                    "2" -> {
                        holder.halftimebtn.setBackgroundResource(R.drawable.halftimebackground)
                        holder.fulltimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                        holder.overtimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                        holder.holidaybtn.setBackgroundResource(R.drawable.selecttimebackground)
                        holder.halftimebtn.setTextColor(Color.parseColor("#ffffff"))
                    }
                    "3" -> {
                        holder.overtimebtn.setBackgroundResource(R.drawable.overtimebackground)
                        holder.fulltimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                        holder.halftimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                        holder.holidaybtn.setBackgroundResource(R.drawable.selecttimebackground)
                        holder.overtimebtn.setTextColor(Color.parseColor("#ffffff"))
                    }

                    "4" -> {
                        holder.holidaybtn.setBackgroundResource(R.drawable.timeholidaybackground)
                        holder.fulltimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                        holder.halftimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                        holder.overtimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                        holder.holidaybtn.setTextColor(Color.parseColor("#ffffff"))
                    }
                }

            }

        }

        holder.itemView.setOnClickListener {
            val action = Front_FragmentDirections.actionFrontFragmentToHomeFragment(person, mobile)
            Navigation.findNavController(it).navigate(action)
        }


    }


    private fun savedata(person: String) {

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = sdf.format(Date())

        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        database = FirebaseDatabase.getInstance().getReference(currentUser!!.uid).child("Members")
            .child(person).child("data")

        database.child(currentDate).setValue(time)


    }


    override fun getItemCount(): Int {
        return userArrayList.size
    }

    class front_viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nametxt = itemView.findViewById<TextView>(R.id.nameText)
        val mobiletxt = itemView.findViewById<TextView>(R.id.mobileText)
        val fulltimebtn = itemView.findViewById<TextView>(R.id.fulltimebtn)
        val halftimebtn = itemView.findViewById<TextView>(R.id.halftimebtn)
        val overtimebtn = itemView.findViewById<TextView>(R.id.overtimebtn)
        val holidaybtn = itemView.findViewById<TextView>(R.id.holidaybtn)
        val deletebtn = itemView.findViewById<View>(R.id.deletebtn)
        val totalhaazritext = itemView.findViewById<TextView>(R.id.totalHaazRiText)
        val logotext = itemView.findViewById<TextView>(R.id.member_image)

    }

}
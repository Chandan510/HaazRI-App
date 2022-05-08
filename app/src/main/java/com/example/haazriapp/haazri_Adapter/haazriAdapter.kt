package com.example.haazriapp.haazri_Adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.haazriapp.*
import com.example.haazriapp.ModelDataClass.all_data



import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.attendence_rcv.view.*
import java.util.*
import kotlin.collections.ArrayList


class haazriAdapter(
    private val dt: ArrayList<all_data>
) :
    RecyclerView.Adapter<haazriAdapter.ItemviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemviewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemviewHolder(inflater.inflate(R.layout.attendence_rcv, parent, false))
        return view
    }


    override fun onBindViewHolder(holder: ItemviewHolder, position: Int) {


        holder.datetext.text = dt[position].date

        when(dt[position].time){

            "1" -> {holder.fulltimebtn.setBackgroundResource(R.drawable.timebackground)
                holder.halftimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                holder.halftimebtn.setTextColor(Color.parseColor("#000000"))
                holder.overtimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                holder.overtimebtn.setTextColor(Color.parseColor("#000000"))
                holder.holidaybtn.setBackgroundResource(R.drawable.selecttimebackground)
                holder.holidaybtn.setTextColor(Color.parseColor("#FF1F1F"))
                holder.fulltimebtn.setTextColor(Color.parseColor("#ffffff"))}

            "2" -> {
                holder.halftimebtn.setBackgroundResource(R.drawable.halftimebackground)
                holder.fulltimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                holder.fulltimebtn.setTextColor(Color.parseColor("#000000"))
                holder.overtimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                holder.overtimebtn.setTextColor(Color.parseColor("#000000"))
                holder.holidaybtn.setBackgroundResource(R.drawable.selecttimebackground)
                holder.holidaybtn.setTextColor(Color.parseColor("#FF1F1F"))
                holder.halftimebtn.setTextColor(Color.parseColor("#ffffff"))
            }
            "3" -> {
                holder.overtimebtn.setBackgroundResource(R.drawable.overtimebackground)
                holder.fulltimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                holder.fulltimebtn.setTextColor(Color.parseColor("#000000"))
                holder.halftimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                holder.halftimebtn.setTextColor(Color.parseColor("#000000"))
                holder.holidaybtn.setBackgroundResource(R.drawable.selecttimebackground)
                holder.holidaybtn.setTextColor(Color.parseColor("#FF1F1F"))
                holder.overtimebtn.setTextColor(Color.parseColor("#ffffff"))
            }

            "4" -> {
                holder.holidaybtn.setBackgroundResource(R.drawable.timeholidaybackground)
                holder.fulltimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                holder.fulltimebtn.setTextColor(Color.parseColor("#000000"))
                holder.halftimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                holder.halftimebtn.setTextColor(Color.parseColor("#000000"))
                holder.overtimebtn.setBackgroundResource(R.drawable.selecttimebackground)
                holder.overtimebtn.setTextColor(Color.parseColor("#000000"))
                holder.holidaybtn.setTextColor(Color.parseColor("#ffffff"))
            }
        }


    }


    override fun getItemCount(): Int {
        return dt.size
    }

    class ItemviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val datetext = itemView.findViewById<TextView>(R.id.dateText)
        val fulltimebtn = itemView.findViewById<TextView>(R.id.fulltimebtn01)
        val halftimebtn = itemView.findViewById<TextView>(R.id.halftimebtn01)
        val overtimebtn = itemView.findViewById<TextView>(R.id.overtimebtn01)
        val holidaybtn = itemView.findViewById<TextView>(R.id.holidaybtn01)

    }

}



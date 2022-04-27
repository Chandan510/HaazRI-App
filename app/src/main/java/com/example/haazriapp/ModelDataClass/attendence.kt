package com.example.haazriapp.ModelDataClass

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "attendenceTable")
data class attendence(

    @PrimaryKey(autoGenerate = true)

    var id: Int = 0,

    var time: String = "0",

    var date: String = "0"

)
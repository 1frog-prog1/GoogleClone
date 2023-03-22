package com.example.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class TaskModel (
    @PrimaryKey
    val Id : UUID = UUID.randomUUID(),
    var title : String = "",
    var description : String = "",
    var Date : Date? = null,
    var isSolved : Boolean = false,
    var isMarked : Boolean = false
)
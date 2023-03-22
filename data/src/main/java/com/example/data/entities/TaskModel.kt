package com.example.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Tasks")
data class TaskModel (
    @PrimaryKey
    val Id : UUID = UUID.randomUUID(),
    var title : String = "",
    var description : String = "",
    var date : Date? = null,
    @ColumnInfo(name = "is_solved")
    var isSolved : Boolean = false,
    @ColumnInfo(
        name = "is_marked",
        defaultValue = "0"
    )
    var isMarked : Boolean
)
package com.example.data.databases

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.data.entities.TaskModel

@Database(
    entities = [ TaskModel::class ],
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {

}
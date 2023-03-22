package com.example.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.entities.TaskModel
import com.example.data.databases.type_converters.TaskTypeConverters

@Database(
    entities = [ TaskModel::class ],
    version = 1
)
@TypeConverters(TaskTypeConverters::class)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

}
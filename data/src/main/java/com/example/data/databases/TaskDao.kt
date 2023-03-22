package com.example.data.databases

import androidx.room.Dao
import androidx.room.Query
import com.example.data.entities.TaskModel
import java.util.UUID


@Dao
interface TaskDao {

    @Query("SELECT * FROM Tasks")
    fun getTasks() : List<TaskModel>

    @Query("SELECT * FROM Tasks " +
            "WHERE Id =(:id)")
    fun getTask(id : UUID) : TaskModel?

}
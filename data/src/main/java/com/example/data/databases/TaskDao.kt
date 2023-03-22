package com.example.data.databases

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.entities.TaskModel
import java.util.UUID


@Dao
interface TaskDao {

    @Query("SELECT * FROM Tasks")
    fun getTasks() : LiveData<List<TaskModel>>

    @Query("SELECT * FROM Tasks " +
            "WHERE Id =(:id)")
    fun getTask(id : UUID) : LiveData<TaskModel?>

    @Insert
    fun insert(task : TaskModel)

    @Update
    fun update(task : TaskModel)

    @Delete
    fun delete(task: TaskModel)

    @Delete
    fun deleteMarked(vararg task: TaskModel)

}
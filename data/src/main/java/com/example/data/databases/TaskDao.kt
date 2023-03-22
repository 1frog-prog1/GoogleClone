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
    suspend fun insert(task : TaskModel)

    @Update
    suspend fun update(task : TaskModel)

    @Delete
    suspend fun delete(task: TaskModel)

    @Query("DELETE FROM Tasks WHERE is_solved = 1")
    suspend fun deleteSolved()

}
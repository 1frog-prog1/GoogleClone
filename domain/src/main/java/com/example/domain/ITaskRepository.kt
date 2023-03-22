package com.example.domain

import androidx.lifecycle.LiveData
import com.example.domain.models.TaskDomain
import java.util.UUID

interface ITaskRepository {

    fun getTasks() : LiveData<List<TaskDomain>>

    fun getTask(id : UUID) : LiveData<TaskDomain?>

}
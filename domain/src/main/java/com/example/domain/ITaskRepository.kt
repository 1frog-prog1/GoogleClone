package com.example.domain

import com.example.domain.models.TaskDomain
import java.util.UUID

interface ITaskRepository {

    fun getTasks() : List<TaskDomain>

    fun getTask(id : UUID) : TaskDomain?

}
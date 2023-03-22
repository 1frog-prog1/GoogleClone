package com.example.googletask.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.data.TaskRepository
import com.example.domain.models.TaskDomain
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val TAG = "ListViewModel"

class ListViewModel : ViewModel() {

    private val taskRepository = TaskRepository.get()

    val tasksLiveData = taskRepository.getTasks()

    fun addTask(task : TaskDomain) {
        taskRepository.create(task)
    }

    fun saveTask(task : TaskDomain) {
        taskRepository.update(task)
    }

}

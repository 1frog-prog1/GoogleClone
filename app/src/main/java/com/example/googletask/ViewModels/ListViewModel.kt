package com.example.googletask.ViewModels

import androidx.lifecycle.ViewModel
import com.example.data.TaskRepository
import com.example.domain.models.TaskDomain

class ListViewModel : ViewModel() {

    private val taskRepository = TaskRepository.get()

    val tasksLiveData = taskRepository.getTasks()

}

package com.example.googletask.ViewModels

import androidx.lifecycle.ViewModel
import com.example.data.TaskRepository

class ListViewModel : ViewModel() {

    private val taskRepository = TaskRepository.get()

    val tasksLiveData = taskRepository.getTasks()

}
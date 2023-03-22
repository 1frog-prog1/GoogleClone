package com.example.googletask.ViewModels

import androidx.lifecycle.ViewModel
import com.example.googletask.models.TaskDomain

class ListViewModel : ViewModel() {
    var tasks = mutableListOf<TaskDomain>()

    // todo: delete it after example

    init {
        for (i in 0 until 20) {
            val task = TaskDomain()
            task.title = "Task #$i"
            task.description = "Description #$i"
            task.isSolved = i % 4 == 0
            task.isMarked = i % 7 == 2
            tasks += task
        }
    }

}
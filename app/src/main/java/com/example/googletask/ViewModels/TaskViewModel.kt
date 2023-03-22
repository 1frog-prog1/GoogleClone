package com.example.googletask.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.data.TaskRepository
import com.example.domain.models.TaskDomain
import com.google.android.material.tabs.TabLayout.TabGravity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID

const private val TAG = "TaskViewModel"

class TaskViewModel : ViewModel() {

    private val taskRepository = TaskRepository.get()
    private val taskIdLiveData = MutableLiveData<UUID>()

    var taskLiveData : LiveData<TaskDomain?> =
        Transformations.switchMap(taskIdLiveData) {
            taskId -> taskRepository.getTask(taskId)
        }

    fun loadTask(taskId : UUID) {
        taskIdLiveData.value = taskId
    }

    fun saveTask(task : TaskDomain) {
        taskRepository.update(task)
    }

}
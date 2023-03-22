package com.example.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Room
import com.example.data.databases.TaskDatabase
import com.example.domain.ITaskRepository
import com.example.domain.models.TaskDomain
import java.util.*

private const val DATABASE_NAME = "task-database"

class TaskRepository
    private constructor(context: Context)
    : ITaskRepository {

    private val db : TaskDatabase = Room.databaseBuilder(
        context.applicationContext,
        TaskDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val taskDao = db.taskDao()

        companion object {
            private var INSTANCE: TaskRepository? = null

            fun initialize(context: Context) {
                if(INSTANCE == null)
                    INSTANCE = TaskRepository(context)
            }

            fun get() : TaskRepository {
                return INSTANCE ?:
                    throw IllegalStateException("TaskRepository must be initialized")

            }

        }

    override fun getTasks(): LiveData<List<TaskDomain>> {
        val modelList = taskDao.getTasks()
        val domainList = Transformations.map(modelList) {
            it -> it.map { TaskConverter.toDomain(it) }
        }
        return domainList
    }

    override fun getTask(id: UUID): LiveData<TaskDomain?> {
        val model = taskDao.getTask(id)
        val domain = Transformations.map(model) {
                TaskConverter.toNullDomain(it)
        }
        return domain
    }

    override suspend fun create(task: TaskDomain) {
        val model = TaskConverter.toModel(task)
        taskDao.insert(model)
    }

    override suspend fun update(task: TaskDomain) {
        val model = TaskConverter.toModel(task)
        taskDao.update(model)
    }

    override suspend fun delete(task: TaskDomain) {
        val model = TaskConverter.toModel(task)
        taskDao.delete(model)
    }

    override suspend fun deleteSolved() {
        taskDao.deleteSolved()
    }


}


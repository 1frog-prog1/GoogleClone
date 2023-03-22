package com.example.data

import com.example.data.entities.TaskModel
import com.example.domain.models.TaskDomain

class TaskConverter private constructor() {
    companion object {

        fun toNullDomain(taskModel: TaskModel?): TaskDomain? {
            return if (taskModel == null)
                null
            else {
                TaskDomain(
                    taskModel.Id,
                    taskModel.title,
                    taskModel.description,
                    taskModel.date,
                    taskModel.isSolved,
                    taskModel.isMarked
                )
            }
        }

        fun toDomain(taskModel: TaskModel): TaskDomain {
            return TaskDomain(
                taskModel.Id,
                taskModel.title,
                taskModel.description,
                taskModel.date,
                taskModel.isSolved,
                taskModel.isMarked
            )
        }


        fun toModel(taskDomain: TaskDomain): TaskModel {
            return TaskModel(
                taskDomain.Id,
                taskDomain.title,
                taskDomain.description,
                taskDomain.date,
                taskDomain.isSolved,
                taskDomain.isMarked
            )
        }


    }
}
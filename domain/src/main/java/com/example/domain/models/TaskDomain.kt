package com.example.domain.models

import java.util.Date
import java.util.UUID

data class TaskDomain (
    val Id : UUID = UUID.randomUUID(),
    var title : String = "",
    var description : String = "",
    var date : Date? = null,
    var isSolved : Boolean = false,
    var isMarked : Boolean = false
        )
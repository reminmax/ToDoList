package com.reminmax.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoItem(

    @PrimaryKey
    val id: Int?,

    val title: String,
    val description: String?,
    val isDone: Boolean
)

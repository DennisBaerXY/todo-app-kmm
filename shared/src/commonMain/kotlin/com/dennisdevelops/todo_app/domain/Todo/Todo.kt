package com.dennisdevelops.todo_app.domain.Todo

import kotlinx.datetime.LocalDateTime

data class Todo(
    val id: Long?,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val date: LocalDateTime
)

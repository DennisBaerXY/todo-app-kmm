package com.dennisdevelops.todo_app.data.Todo.Mapper

import com.dennisdevelops.todo_app.domain.Todo.Todo
import com.dennisdevelops.todo_app.utils.DateTimeUtils
import database.TodoEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun TodoEntity.toTodo(): Todo {
    return Todo(
        id = id,
        title = title,
        description = description,
        isCompleted = isDone.toInt() == 1,
        date = Instant.fromEpochMilliseconds(date.toLong()).toLocalDateTime(TimeZone.currentSystemDefault())
    )
}
package com.dennisdevelops.todo_app.data.local

import com.dennisdevelops.todoapp.TodoDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(TodoDatabase.Schema, name = "todo.db")
    }
}
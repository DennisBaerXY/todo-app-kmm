package com.dennisdevelops.todo_app.data.local

import android.content.Context
import com.dennisdevelops.todoapp.TodoDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TodoDatabase.Schema, context, "TodoDatabase.db")
    }

}
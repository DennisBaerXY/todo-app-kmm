package com.dennisdevelops.todo_app.android.todo_list

import com.dennisdevelops.todo_app.domain.Todo.Todo

data class TodoListState (
    val todoList: List<Todo> = emptyList(),
    val query: String = "",
    val isQuerryExtended: Boolean = false,
    val isLoading: Boolean = false,

)
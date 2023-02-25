package com.dennisdevelops.todo_app.domain.Todo

interface TodoDataSource {
    suspend fun insertTodo(todo: Todo)
    suspend fun getTodoById(id: Long): Todo?
    suspend fun getAllTodos(): List<Todo>
    suspend fun deleteTodoById(id: Long)

}
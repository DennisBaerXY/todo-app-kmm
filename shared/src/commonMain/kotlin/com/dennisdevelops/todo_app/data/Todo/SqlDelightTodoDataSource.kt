package com.dennisdevelops.todo_app.data.Todo

import com.dennisdevelops.todo_app.data.Todo.Mapper.toTodo
import com.dennisdevelops.todo_app.domain.Todo.Todo
import com.dennisdevelops.todo_app.domain.Todo.TodoDataSource
import com.dennisdevelops.todo_app.utils.DateTimeUtils
import com.dennisdevelops.todoapp.TodoDatabase

class SqlDelightTodoDataSource(db: TodoDatabase) : TodoDataSource {
    private val todoQueries = db.todoQueries

    override suspend fun insertTodo(todo: Todo) {
        todoQueries.insertTodo(
            id = todo.id,
            title = todo.title,
            description = todo.description,
            isDone = if(todo.isCompleted) 1 else 0 ,
            date = DateTimeUtils.toEpochMilliseconds(todo.date).toString()
        )
    }

    override suspend fun getTodoById(id: Long): Todo? {
        return todoQueries.getTodoById(id).executeAsOneOrNull()?.let {
            it.toTodo()
        }
    }

    override suspend fun getAllTodos(): List<Todo> {
        return todoQueries.getAllTodos().executeAsList().map {
            it.toTodo()
        }
    }

    override suspend fun deleteTodoById(id: Long) {
        todoQueries.deleteTodo(id)
    }
}

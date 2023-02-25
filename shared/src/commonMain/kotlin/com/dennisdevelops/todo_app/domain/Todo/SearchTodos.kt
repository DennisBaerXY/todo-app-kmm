package com.dennisdevelops.todo_app.domain.Todo

class SearchTodos {

    fun execute( todos: List<Todo>, query: String): List<Todo> {
        if(query.isBlank()) {
            return todos
        }
        return todos.filter { todo ->
            todo.title.contains(query, true) || todo.description.contains(query, true)
        }



    }
}
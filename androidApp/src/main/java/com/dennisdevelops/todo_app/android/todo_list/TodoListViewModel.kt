package com.dennisdevelops.todo_app.android.todo_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennisdevelops.todo_app.domain.Todo.SearchTodos
import com.dennisdevelops.todo_app.domain.Todo.Todo
import com.dennisdevelops.todo_app.domain.Todo.TodoDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel  @Inject constructor(
    private val todoDataSource: TodoDataSource,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val searchTodos = SearchTodos()

    val todos = savedStateHandle.getStateFlow("todos", emptyList<Todo>())
    val query = savedStateHandle.getStateFlow("query", "")
    val isQueryExtended = savedStateHandle.getStateFlow("isQueryExtended", false)
    val isLoading = savedStateHandle.getStateFlow("isLoading", false)

    val state = combine(
        todos,
        query,
        isQueryExtended,
        isLoading
    ) { todos, query, isQueryExtended, isLoading ->
        TodoListState(
            todoList = if(isQueryExtended) searchTodos.execute(todos, query) else todos,
            query = query,
            isQuerryExtended = isQueryExtended,
            isLoading = isLoading
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TodoListState())


    fun loadTodos() {
        viewModelScope.launch {
            savedStateHandle["isLoading"] = true
            savedStateHandle["todos"] = todoDataSource.getAllTodos()
            savedStateHandle["isLoading"] = false

        }
    }

    fun onQueryChanged(query: String) {
        savedStateHandle["query"] = query
    }

    fun onToggleSearchView() {
        savedStateHandle["isQueryExtended"] = !isQueryExtended.value
        if(!isQueryExtended.value) {
            savedStateHandle["query"] = ""
        }
    }

    fun deleteTodoById(id: Long) {
        viewModelScope.launch {
            todoDataSource.deleteTodoById(id)
            loadTodos()
        }
    }


}
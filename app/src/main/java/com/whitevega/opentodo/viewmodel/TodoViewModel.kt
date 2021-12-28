package com.whitevega.opentodo.viewmodel

import androidx.lifecycle.*
import com.whitevega.opentodo.data.Todo
import com.whitevega.opentodo.data.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    val allTodos: LiveData<List<Todo>> = repository.allTodos.asLiveData()

    fun insert(todo: Todo) = viewModelScope.launch {
        repository.insert(todo)
    }

    fun delete(todo: Todo) = viewModelScope.launch {
        repository.delete(todo)
    }

    fun update(todo: Todo) = viewModelScope.launch {
        repository.update(todo)
    }
}

class TodoViewModelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
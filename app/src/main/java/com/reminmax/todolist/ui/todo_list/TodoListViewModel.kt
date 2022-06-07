package com.reminmax.todolist.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reminmax.todolist.data.TodoItem
import com.reminmax.todolist.data.TodoRepository
import com.reminmax.todolist.util.Routes
import com.reminmax.todolist.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {

    val todos = repository.getTodoItems()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var deletedTodoItem: TodoItem? = null

    fun onEvent(event: TodoListEvent) {
        when(event) {
            is TodoListEvent.OnTodoItemClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todoItem.id}"))
            }
            is TodoListEvent.OnAddTodoItemClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodoListEvent.OnUndoDeleteClick -> {
                //  восстановление ранее удаленного элемента списка
                deletedTodoItem?.let { todoItem ->
                    viewModelScope.launch {
                        repository.insertTodoItem(todoItem)
                    }
                }
            }
            is TodoListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    // перед удалением сохраняем ссылку на элемент, чтобы потом его можно было восстановить
                    deletedTodoItem = event.todo
                    repository.deleteTodoItem(event.todo)
                    // вывод сообщения на экран
                    sendUiEvent(UiEvent.ShowSnackBar(
                        message = "Todo item deleted",
                        action = "undo"
                    ))
                }
            }
            is TodoListEvent.OnDoneChange -> {
                // для изменения isDone применяется вставка
                // в соотв с тек. настройками, при вставке элемента с таким же id он будет перезаписан
                viewModelScope.launch {
                    repository.insertTodoItem(
                        event.todoItem.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
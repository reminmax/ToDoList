package com.reminmax.todolist.ui.todo_list

import com.reminmax.todolist.data.TodoItem

sealed class TodoListEvent {

    data class OnDeleteTodoClick(val todo: TodoItem): TodoListEvent()

    data class OnDoneChange(val todoItem: TodoItem, val isDone: Boolean): TodoListEvent()

    object OnUndoDeleteClick: TodoListEvent()

    data class OnTodoItemClick(val todoItem: TodoItem): TodoListEvent()

    object OnAddTodoItemClick: TodoListEvent()
}

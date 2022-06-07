package com.reminmax.todolist.ui.todo_list

import com.reminmax.todolist.data.TodoItem

sealed class TodoListEvent {

    // удаление элемента списка
    data class OnDeleteTodoClick(val todo: TodoItem): TodoListEvent()

    // изменение статуса задачи (выполнена/не выполнена)
    data class OnDoneChange(val todoItem: TodoItem, val isDone: Boolean): TodoListEvent()

    // object (а не class) потому что не нужно хранить состояние (данные) и нет параметров
    object OnUndoDeleteClick: TodoListEvent()

    // переход на экран с детальной информацией при клике на элемент списка
    data class OnTodoItemClick(val todoItem: TodoItem): TodoListEvent()

    // добавление нового элемента списка
    // в данном конкретном случае это нажатие на FloatingActionButton
    // не class, так как нет параметров
    object OnAddTodoItemClick: TodoListEvent()
}

package com.reminmax.todolist.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodoItem(todoItem: TodoItem)

    suspend fun deleteTodoItem(todoItem: TodoItem)

    suspend fun getTodoItemById(id: Int): TodoItem?

    fun getTodoItems(): Flow<List<TodoItem>>

}
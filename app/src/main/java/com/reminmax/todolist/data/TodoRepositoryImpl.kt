package com.reminmax.todolist.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao: TodoDao
): TodoRepository {

    override suspend fun insertTodoItem(todoItem: TodoItem) {
        dao.insertTodoItem(todoItem)
    }

    override suspend fun deleteTodoItem(todoItem: TodoItem) {
        dao.deleteTodoItem(todoItem)
    }

    override suspend fun getTodoItemById(id: Int): TodoItem? {
        return dao.getTodoItemById(id)
    }

    override fun getTodoItems(): Flow<List<TodoItem>> {
        return dao.getTodoItems()
    }
}
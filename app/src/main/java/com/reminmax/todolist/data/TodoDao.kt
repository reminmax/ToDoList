package com.reminmax.todolist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodoItem(todoItem: TodoItem)

    @Delete
    suspend fun deleteTodoItem(todoItem: TodoItem)

    @Query("SELECT * FROM todoitem WHERE id = :id")
    suspend fun getTodoItemById(id: Int): TodoItem?

    @Query("SELECT * FROM todoitem")
    fun getTodoItems(): Flow<List<TodoItem>>

}
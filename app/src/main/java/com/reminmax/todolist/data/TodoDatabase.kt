package com.reminmax.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TodoItem::class],

    // данную версию следует изменять при внесении изменений в структуру бд,
    // чтобы ROOM смог правильно выполнить migration
    version = 1
)
abstract class TodoDatabase: RoomDatabase() {

    abstract val dao: TodoDao

}
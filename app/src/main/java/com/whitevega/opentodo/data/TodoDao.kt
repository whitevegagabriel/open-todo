package com.whitevega.opentodo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    fun getAll(): Flow<List<Todo>>

    @Insert
    suspend fun insert(todo: Todo)

    @Update
    suspend fun updateTodos(vararg todos: Todo)

    @Delete
    suspend fun deleteTodos(vararg todos: Todo)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()
}
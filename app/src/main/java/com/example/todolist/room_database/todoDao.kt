package com.example.todolist.room_database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface todoDao {
    @Insert
    suspend fun insert(todoEntity: todoEntity)

    @Update
    suspend fun update(todoEntity: todoEntity)

    @Delete
    suspend fun delete(todoEntity: todoEntity)

    @Query("SELECT * FROM `todo-table`")
    fun fetchAllTodo(): Flow<List<todoEntity>>

    @Query("SELECT * FROM `todo-table` where uid=:id")
    fun fetchById(id:Int): Flow<todoEntity>
}
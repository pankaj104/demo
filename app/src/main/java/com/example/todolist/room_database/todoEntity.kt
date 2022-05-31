package com.example.todolist.room_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo-table")
data class todoEntity(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo(name = "task_name") val task_name: String = "",
    @ColumnInfo(name = "initial_value") val initial_value: Int,
    @ColumnInfo(name = "final_value") val final_value: Int
)

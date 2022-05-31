package com.example.todolist.room_database

import android.app.Application

class todoApp: Application() {
    val db by lazy {
        todoDatabase.getInstance(this)
    }
}
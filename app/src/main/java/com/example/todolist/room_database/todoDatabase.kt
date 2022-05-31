package com.example.todolist.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [todoEntity::class], version = 1)
abstract class todoDatabase: RoomDatabase() {
    abstract fun todoDao(): todoDao

    companion object{
        @Volatile
        private var INSTANCE: todoDatabase? = null

        fun getInstance(context: Context): todoDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext, todoDatabase::class.java, "todo-database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
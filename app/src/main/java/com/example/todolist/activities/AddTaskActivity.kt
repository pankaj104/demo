package com.example.todolist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.todolist.R
import com.example.todolist.databinding.ActivityAddTaskBinding
import com.example.todolist.room_database.todoApp
import com.example.todolist.room_database.todoDao
import com.example.todolist.room_database.todoEntity
import kotlinx.coroutines.launch

class AddTaskActivity : AppCompatActivity() {
    var binding: ActivityAddTaskBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupActionBar()

        binding?.btnSaveTask?.setOnClickListener {
            formValidation()
        }

    }

    private fun formValidation(){
        if (binding?.etTaskName?.text.isNullOrEmpty()){
            Toast.makeText(this, "Please Enter Task Name!", Toast.LENGTH_SHORT).show()
        }else if (binding?.etInitialValue?.text.isNullOrEmpty()){
            Toast.makeText(this, "Please Enter Initial Value!", Toast.LENGTH_SHORT).show()
        }else if (binding?.etFinalValue?.text.isNullOrEmpty()){
            Toast.makeText(this, "Please Enter Final Value!", Toast.LENGTH_SHORT).show()
        }else{
            val todoDao = (application as todoApp).db.todoDao()
            addToDatabase(todoDao)
        }
    }

    private fun addToDatabase(todoDao: todoDao){
        val task_name = binding?.etTaskName?.text.toString()
        val initialValue:Int = Integer.parseInt(binding?.etInitialValue?.text.toString())
        val finalValue:Int = Integer.parseInt(binding?.etFinalValue?.text.toString())
        lifecycleScope.launch {
            todoDao.insert(todoEntity(task_name = task_name, initial_value = initialValue, final_value = finalValue))
            Toast.makeText(this@AddTaskActivity, "Task Added Successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(binding?.toolbarAddTaskActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_indicator)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding?.toolbarAddTaskActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
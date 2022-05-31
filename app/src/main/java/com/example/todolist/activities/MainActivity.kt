package com.example.todolist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.recyclerView.ItemAdapter
import com.example.todolist.room_database.todoApp
import com.example.todolist.room_database.todoEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupActionBar()
        binding?.addTaskBtn?.setOnClickListener {
//            val intent = Intent(this, AddTaskActivity::class.java)
//            startActivity(intent)

            testfuction(homeFragment())
        }
        val todoDao = (application as todoApp).db.todoDao()
        lifecycleScope.launch {
            todoDao.fetchAllTodo().collect{
                populateTodoEntityToUi(ArrayList(it))
            }
        }
    }

    private fun testfuction(fragment: homeFragment) {
        val fragmentLayout = supportFragmentManager
        val fragmentTransation = fragmentLayout.beginTransaction()
        fragmentTransation.replace(R.id.container, fragment)
        fragmentTransation.commit()
    }

    fun populateTodoEntityToUi(list: ArrayList<todoEntity>){
        if (list.size > 0){
            recyclerView_mainActivity.visibility = View.VISIBLE
            no_task_available.visibility = View.GONE
            recyclerView_mainActivity.layoutManager = LinearLayoutManager(this)
            recyclerView_mainActivity.setHasFixedSize(true)

            val adapter = ItemAdapter(this, list)
            recyclerView_mainActivity.adapter = adapter

            adapter.setOnClickListener(object : ItemAdapter.OnClickListener{
                override fun onClick(position: Int, model: todoEntity) {
                    //Toast.makeText(this@MainActivity, "${model.task_name} + $position", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity, EditTaskActivity::class.java)
                    intent.putExtra("position",position)
                    startActivity(intent)
                }

                override fun onClick(v: View?) {
                    //nothing
                }
            })
        }else{
            no_task_available.visibility = View.VISIBLE
            recyclerView_mainActivity.visibility = View.GONE
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(binding?.toolbarMainActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_indicator)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding?.toolbarMainActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
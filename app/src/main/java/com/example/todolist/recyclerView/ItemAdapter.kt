package com.example.todolist.recyclerView

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.room_database.todoEntity
import kotlinx.android.synthetic.main.item_row.view.*

class ItemAdapter(val context: Context, val list: ArrayList<todoEntity>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view)
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if (holder is MyViewHolder){
            holder.itemView.rv_task_name.text = model.task_name
            holder.itemView.rv_progress_text.text = "${model.initial_value}/${model.final_value}"
            holder.itemView.progressBar.max = model.final_value
            holder.itemView.progressBar.progress = model.initial_value
            holder.itemView.setOnClickListener {
                onClickListener!!.onClick(position, model)
            }
        }
    }

    interface OnClickListener : View.OnClickListener {
        fun onClick(position: Int, model: todoEntity)
    }
    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
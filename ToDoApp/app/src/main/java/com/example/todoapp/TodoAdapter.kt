package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Random

class TodoAdapter(val list: List<TodoModel>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo,parent,false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position])
    }


    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(todoModel: TodoModel) {
            with(itemView){
                val colors = resources.getIntArray(R.array.random_color)
                val randomColor = colors[Random().nextInt(colors.size)]
                findViewById<View>(R.id.viewColorTag).setBackgroundColor(randomColor)

                findViewById<TextView>(R.id.tvShowTitle).text = todoModel.title
                findViewById<TextView>(R.id.tvShowTask).text = todoModel.description
                findViewById<TextView>(R.id.tvShowCategory).text = todoModel.category
                updateTime(todoModel.time)
                updateDate(todoModel.date)
            }
        }
        private fun updateTime(time: Long) {
            // Hour minute AM:PM
            val myFormat = "h:mm a"
            val sdf = SimpleDateFormat(myFormat)
            itemView.findViewById<TextView>(R.id.tvShowTime).text = sdf.format(Date(time))
        }
        private fun updateDate(date: Long) {
            // Sat, 28 Dec 2024
            val myFormat = "EEE, d MMM yyyy"
            val sdf = SimpleDateFormat(myFormat)
            itemView.findViewById<TextView>(R.id.tvShowDate).text = sdf.format(Date(date))
        }
    }

}
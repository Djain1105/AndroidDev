package com.example.todo

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.db.MyDbHelper
import com.example.todo.db.TodoTable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val todos = ArrayList<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val todoAdapter = ArrayAdapter<Todo>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            todos)

        val db = MyDbHelper(this).writableDatabase

        fun refreshTodoList() {
            val todolist = TodoTable.getAllTodos(db)
            todos.clear()
            todos.addAll(todolist)
            todoAdapter.notifyDataSetChanged()
        }

        binding.lvTodo.adapter = todoAdapter
        refreshTodoList()
        binding.btnAddTodo.setOnClickListener {
            val newTodo = Todo(
                binding.edNewTodo.text.toString(),
                false
            )
            TodoTable.insertTodo(db,newTodo)
            refreshTodoList()
        }

    }
}
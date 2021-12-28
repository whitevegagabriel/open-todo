package com.whitevega.opentodo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.whitevega.opentodo.R
import com.whitevega.opentodo.TodoApplication
import com.whitevega.opentodo.adapter.TodoListAdapter
import com.whitevega.opentodo.data.Todo
import com.whitevega.opentodo.databinding.ActivityMainBinding
import com.whitevega.opentodo.viewmodel.MainActivityViewModel
import com.whitevega.opentodo.viewmodel.TodoViewModel
import com.whitevega.opentodo.viewmodel.TodoViewModelFactory

class MainActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private lateinit var viewModel: MainActivityViewModel
    private  lateinit var binding: ActivityMainBinding
    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory((application as TodoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TodoListAdapter(this::deleteListItem, this::editListItem)
        binding.activityMainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.activityMainRecyclerView.adapter = adapter

        todoViewModel.allTodos.observe(this, { todos ->
            todos?.let { adapter.submitList(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.activity_main_plus_button)
        fab.setOnClickListener {
            addListItem(it)
        }
    }

    private fun addListItem(view: View) {
        if (view.id == R.id.activity_main_plus_button) {
            val todo = Todo(0, false, "")
            todoViewModel.insert(todo)
            Log.d(TAG, "item inserted")
        }
    }

    private fun deleteListItem(todo: Todo) {
        todoViewModel.delete(todo)
    }

    private fun editListItem(todo: Todo, newText: String) {
        val newTodo = Todo(todo.id, todo.checked, newText)
        todoViewModel.update(newTodo)
    }

}
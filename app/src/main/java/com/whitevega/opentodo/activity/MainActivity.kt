package com.whitevega.opentodo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.whitevega.opentodo.R
import com.whitevega.opentodo.adapter.ListAdapter
import com.whitevega.opentodo.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel = MainActivityViewModel()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.main_activity_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (!viewModel.initialized) viewModel.initialize()

        val adapter = ListAdapter(viewModel.data)
        recyclerView.adapter = adapter
    }
}
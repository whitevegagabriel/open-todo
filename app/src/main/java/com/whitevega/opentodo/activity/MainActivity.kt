package com.whitevega.opentodo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.whitevega.opentodo.R
import com.whitevega.opentodo.adapter.ListAdapter
import com.whitevega.opentodo.databinding.ActivityMainBinding
import com.whitevega.opentodo.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel = MainActivityViewModel()
    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityMainRecyclerView.layoutManager = LinearLayoutManager(this)

        if (!viewModel.initialized) viewModel.initialize()

        val adapter = ListAdapter(viewModel.data)
        binding.activityMainRecyclerView.adapter = adapter
    }
}
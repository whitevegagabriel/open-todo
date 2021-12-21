package com.whitevega.opentodo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.whitevega.opentodo.adapter.ListAdapter
import com.whitevega.opentodo.databinding.ActivityMainBinding
import com.whitevega.opentodo.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityMainRecyclerView.layoutManager = LinearLayoutManager(this)

        if (!viewModel.initialized) viewModel.initialize()

        val adapter = ListAdapter(viewModel.data)
        binding.activityMainRecyclerView.adapter = adapter
    }
}
package com.whitevega.opentodo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.whitevega.opentodo.R
import com.whitevega.opentodo.adapter.ListAdapter
import com.whitevega.opentodo.databinding.ActivityMainBinding
import com.whitevega.opentodo.viewmodel.ListItemViewModel
import com.whitevega.opentodo.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private lateinit var viewModel: MainActivityViewModel
    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityMainRecyclerView.layoutManager = LinearLayoutManager(this)

        if (!viewModel.initialized) viewModel.initialize()

        val adapter = ListAdapter(viewModel.data, this::checkBoxToggled)
        binding.activityMainRecyclerView.adapter = adapter
    }

    fun addListItem(view: View) {
        if (view.id == R.id.activity_main_plus_button) {
            viewModel.data.add(ListItemViewModel())
            binding.activityMainRecyclerView.adapter?.notifyItemInserted(viewModel.data.size-1)
            Log.d(TAG, "item inserted")
        }
    }

    private fun checkBoxToggled(v: View, mListItemViewModel: ListItemViewModel) {
        viewModel.toggleListItem(mListItemViewModel)
        val position = viewModel.deleteListItem(mListItemViewModel)
        Log.d(TAG, "item removed")
        binding.activityMainRecyclerView.adapter?.notifyItemRemoved(position)
    }
}
package com.whitevega.opentodo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.whitevega.opentodo.R
import com.whitevega.opentodo.viewmodel.ListItemViewModel

class ListAdapter(private val mList: List<ListItemViewModel>, private val checkListener: (View, ListItemViewModel) -> Unit) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mListItemViewModel = mList[position]

        holder.itemView.findViewById<EditText>(R.id.list_item_edit_text).setText(mListItemViewModel.text)

        val checkBoxView: CheckBox = holder.itemView.findViewById(R.id.list_item_check_box)
        checkBoxView.setOnClickListener {
                    checkListener(it, mListItemViewModel)}

        if (checkBoxView.isChecked != mListItemViewModel.checked) checkBoxView.toggle()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val editText: EditText = itemView.findViewById(R.id.list_item_edit_text)
    }
}
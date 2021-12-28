package com.whitevega.opentodo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.whitevega.opentodo.R
import com.whitevega.opentodo.data.Todo

class TodoListAdapter(private val checkListener: (Todo) -> Unit, private val editTextListener: (Todo, String) -> Unit) : ListAdapter<Todo, TodoListAdapter.TodoViewHolder>(TodoComparator()) {
    private val TAG = this.javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder.create(parent, checkListener, editTextListener)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.text, current.checked, current)
    }

    class TodoViewHolder(itemView: View, private val checkListener: (Todo) -> Unit, private val editTextListener: (Todo, String) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val TAG = this.javaClass.simpleName
        private val todoEditText: EditText = itemView.findViewById(R.id.recyclerview_item_edit_text)
        private val todoCheckBox: CheckBox = itemView.findViewById(R.id.recyclerview_item_check_box)

        fun bind(text: String?, checked: Boolean?, todo: Todo) {
            todoEditText.setText(text)
            if (todoCheckBox.isChecked != checked) todoCheckBox.toggle()
            todoCheckBox.setOnClickListener { checkListener(todo) }
            todoEditText.setOnFocusChangeListener { _, _ -> editTextListener(todo, todoEditText.text.toString()) }
        }

        companion object {
            fun create(parent: ViewGroup, checkListener: (Todo) -> Unit, editTextListener: (Todo, String) -> Unit): TodoViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return TodoViewHolder(view, checkListener, editTextListener)
            }
        }
    }

    class TodoComparator : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.text == newItem.text && oldItem.checked == newItem.checked
        }
    }
}
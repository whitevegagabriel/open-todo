package com.whitevega.opentodo.viewmodel

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    val data = ArrayList<ListItemViewModel>()
    var initialized = false

    fun initialize() {
        initialized = true
    }

    fun deleteListItem(mListItemViewModel: ListItemViewModel) : Int {
        val index = data.indexOf(mListItemViewModel)
        data.remove(mListItemViewModel)
        return index
    }

    fun toggleListItem(mListItemViewModel: ListItemViewModel) {
        mListItemViewModel.checked = !mListItemViewModel.checked
    }
}
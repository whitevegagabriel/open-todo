package com.whitevega.opentodo.viewmodel

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    val data = ArrayList<ListItemViewModel>()
    var initialized = false

    fun initialize() {
        initialized = true
    }
}
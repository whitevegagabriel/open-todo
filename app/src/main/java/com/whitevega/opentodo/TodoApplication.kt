package com.whitevega.opentodo

import android.app.Application
import com.whitevega.opentodo.data.TodoRepository
import com.whitevega.opentodo.data.TodoRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TodoApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { TodoRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { TodoRepository(database.todoDao()) }
}
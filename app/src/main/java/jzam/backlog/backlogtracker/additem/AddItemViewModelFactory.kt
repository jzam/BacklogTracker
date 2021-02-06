package jzam.backlog.backlogtracker.additem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jzam.backlog.backlogtracker.database.BacklogDatabaseDao

class AddItemViewModelFactory(
        private val dataSource: BacklogDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddItemViewModel::class.java)) {
            return AddItemViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
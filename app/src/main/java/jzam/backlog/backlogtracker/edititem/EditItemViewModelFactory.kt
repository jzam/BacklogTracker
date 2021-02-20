package jzam.backlog.backlogtracker.edititem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jzam.backlog.backlogtracker.database.BacklogDatabaseDao

class EditItemViewModelFactory(
        private val backlogItemKey: Long,
        private val dataSource: BacklogDatabaseDao) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditItemViewModel::class.java)) {
            return EditItemViewModel(backlogItemKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
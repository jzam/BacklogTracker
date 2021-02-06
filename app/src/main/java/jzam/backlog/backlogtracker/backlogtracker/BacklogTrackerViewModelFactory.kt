package jzam.backlog.backlogtracker.backlogtracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jzam.backlog.backlogtracker.database.BacklogDatabaseDao

class BacklogTrackerViewModelFactory(
        private val dataSource: BacklogDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BacklogTrackerViewModel::class.java)) {
            return BacklogTrackerViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
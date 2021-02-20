package jzam.backlog.backlogtracker.backlogtracker

import androidx.lifecycle.*
import jzam.backlog.backlogtracker.database.BacklogDatabaseDao

class BacklogTrackerViewModel(
        dataSource: BacklogDatabaseDao) : ViewModel() {

    val database = dataSource

    val backlog = database.getBacklog()

    private val _navigateToAddItem = MutableLiveData<Boolean>()
    val navigateToAddItem: LiveData<Boolean>
        get() = _navigateToAddItem

    private val _navigateToEditItem = MutableLiveData<Long>()
    val navigateToEditItem: LiveData<Long>
        get() = _navigateToEditItem

    private val _navigateToSearch = MutableLiveData<Boolean>()
    val navigateToSearch: LiveData<Boolean>
        get() = _navigateToSearch

    fun onAddItemClicked() {
        _navigateToAddItem.value = true
    }

    fun onAddItemNavigated() {
        _navigateToAddItem.value = null
    }

    fun onBacklogItemClicked(id: Long) {
        _navigateToEditItem.value = id
    }

    fun onEditItemNavigated() {
        _navigateToEditItem.value = null
    }

    fun onSearchClicked() {
        _navigateToSearch.value = true
    }

    fun onSearchNavigated() {
        _navigateToSearch.value = null
    }
}
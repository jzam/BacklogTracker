package jzam.backlog.backlogtracker.edititem

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jzam.backlog.backlogtracker.database.BacklogDatabaseDao
import jzam.backlog.backlogtracker.database.BacklogItem
import kotlinx.coroutines.launch

class EditItemViewModel(
        private val backlogItemKey: Long = 0L,
        dataSource: BacklogDatabaseDao) : ViewModel() {

    val database = dataSource

    private val item: LiveData<BacklogItem>

    fun getItem() = item

    private val _navigateToBacklogTracker = MutableLiveData<Boolean>()
    val navigateToBacklogTracker: LiveData<Boolean>
        get() = _navigateToBacklogTracker

    val name = MutableLiveData<String>()
    val type = MutableLiveData<String>()
    val sortValue = MutableLiveData<String>()
    val platform = MutableLiveData<String>()
    val genre = MutableLiveData<String>()
    val progressDone = MutableLiveData<String>()
    val progressTotal = MutableLiveData<String>()

    init {
        item = database.getBacklogItemWithId(backlogItemKey)
    }

    fun onCancelClicked() {
        _navigateToBacklogTracker.value = true
    }

    fun onDeleteClicked() {
        viewModelScope.launch { delete() }
        _navigateToBacklogTracker.value = true
    }

    fun onConfirmClicked() {
        viewModelScope.launch { update() }
        _navigateToBacklogTracker.value = true
    }

    fun onBacklogTrackerNavigated() {
        _navigateToBacklogTracker.value = null
    }

    private suspend fun delete() {
        val item = database.get(backlogItemKey)
        Log.i("I/EIVM", "Deleting item of id: ${backlogItemKey}...")
        database.deleteBacklogItem(item)
    }

    private suspend fun update() {
        val item = database.get(backlogItemKey)
        Log.i("I/EIVM", "Updating item of id: ${backlogItemKey}...")
        Log.i("I/EIVM", "Name is ${name.value}")
        Log.i("I/EIVM", "Type is ${type.value}")
        Log.i("I/EIVM", "Sort is ${sortValue.value}")
        Log.i("I/EIVM", "Platform is ${platform.value}")
        Log.i("I/EIVM", "Genre is ${genre.value}")
        Log.i("I/EIVM", "Progress is ${progressDone.value} / ${progressTotal.value}")

        item.name = name.value.toString()
        item.type = type.value.toString()
        item.sortValue = sortValue.value.toString().toInt()
        item.platform = platform.value.toString()
        item.genre = genre.value.toString()
        item.progressDone = progressDone.value.toString().toInt()
        item.progressTotal = progressTotal.value.toString().toInt()
        database.update(item)
    }

}
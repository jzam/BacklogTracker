package jzam.backlog.backlogtracker.additem

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jzam.backlog.backlogtracker.database.BacklogDatabaseDao
import jzam.backlog.backlogtracker.database.BacklogItem
import kotlinx.coroutines.launch

class AddItemViewModel(
        dataSource: BacklogDatabaseDao) : ViewModel() {

    val database = dataSource

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

    fun onCancelClicked() {
        _navigateToBacklogTracker.value = true
    }

    fun onConfirmClicked() {
        viewModelScope.launch { insert() }
        _navigateToBacklogTracker.value = true
    }

    fun onBacklogTrackerNavigated() {
        _navigateToBacklogTracker.value = null
    }

    private suspend fun insert() {
        val item = BacklogItem()
        Log.i("I/AIVM", "Inserting new item...")
        Log.i("I/AIVM", "Name is ${name.value}")
        Log.i("I/AIVM", "Type is ${type.value}")
        Log.i("I/AIVM", "Sort is ${sortValue.value}")
        Log.i("I/AIVM", "Platform is ${platform.value}")
        Log.i("I/AIVM", "Genre is ${genre.value}")
        Log.i("I/AIVM", "Progress is ${progressDone.value} / ${progressTotal.value}")

        item.name = name.value.toString()
        item.type = type.value.toString()
        item.sortValue = sortValue.value.toString().toInt()
        item.platform = platform.value.toString()
        item.genre = genre.value.toString()
        item.progressDone = progressDone.value.toString().toInt()
        item.progressTotal = progressTotal.value.toString().toInt()
        database.insert(item)
    }

}
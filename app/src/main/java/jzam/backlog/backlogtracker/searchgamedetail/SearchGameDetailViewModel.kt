package jzam.backlog.backlogtracker.searchgamedetail

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jzam.backlog.backlogtracker.database.BacklogDatabaseDao
import jzam.backlog.backlogtracker.database.BacklogItem
import jzam.backlog.backlogtracker.network.GameItem
import kotlinx.coroutines.launch

class SearchGameDetailViewModel (private val gameItem: GameItem,
                                 dataSource: BacklogDatabaseDao) : ViewModel() {

    val database = dataSource

    val name = gameItem.name
    val description = gameItem.deck
    val imageUrl = gameItem.image!!.smallUrl

    private val _navigateToSearch = MutableLiveData<String>()
    val navigateToSearch: LiveData<String>
        get() = _navigateToSearch

    val platform = MutableLiveData<String>()
    val genre = MutableLiveData<String>()

    val platformSpinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
            platform.value = parent!!.getItemAtPosition(0) as String
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            platform.value = parent!!.getItemAtPosition(position) as String
        }
    }

    val genreSpinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
            genre.value = parent!!.getItemAtPosition(0) as String
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            genre.value = parent!!.getItemAtPosition(position) as String
        }
    }

    fun onAddClicked() {
        viewModelScope.launch { insert() }
        _navigateToSearch.value = "Added ${name}"
    }

    fun onBackClicked() {
        _navigateToSearch.value = ""
    }

    fun onSearchNavigated() {
        _navigateToSearch.value = null
    }

    private suspend fun insert() {
        val item = BacklogItem()
        Log.i("I/SGDVM", "Inserting new item...")
        Log.i("I/SGDVM", "Name is ${name}")

        //TODO Change this so we get values from preferences or user input
        item.name = name!!
        item.type = "game"
        item.sortValue = 1
        item.platform = platform.value.toString()
        item.genre = genre.value.toString()
        item.progressDone = 0
        item.progressTotal = 100
        item.iconImageUrl = gameItem.image!!.iconUrl
        item.detailImageUrl = gameItem.image!!.mediumUrl
        database.insert(item)
    }
}
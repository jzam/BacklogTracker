package jzam.backlog.backlogtracker.searchgamedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jzam.backlog.backlogtracker.database.BacklogDatabaseDao
import jzam.backlog.backlogtracker.network.GameItem

class SearchGameDetailViewModelFactory(private val gameItem: GameItem,
                                       private val dataSource: BacklogDatabaseDao) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchGameDetailViewModel::class.java)) {
            return SearchGameDetailViewModel(gameItem, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
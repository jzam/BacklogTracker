package jzam.backlog.backlogtracker.searchgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchGameViewModelFactory(private val gameApiKey: String) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchGameViewModel::class.java)) {
            return SearchGameViewModel(gameApiKey) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
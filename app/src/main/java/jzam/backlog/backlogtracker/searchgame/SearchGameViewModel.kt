package jzam.backlog.backlogtracker.searchgame

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jzam.backlog.backlogtracker.network.GameApi
import jzam.backlog.backlogtracker.network.GameItem
import kotlinx.coroutines.launch

class SearchGameViewModel (private val gameApiKey: String) : ViewModel() {
    val searchText = MutableLiveData<String>()

    private val _gameList = MutableLiveData<List<GameItem>>()
    val gameList: LiveData<List<GameItem>>
        get() = _gameList

    private val _navigateToDetail = MutableLiveData<GameItem>()
    val navigateToDetail: LiveData<GameItem>
        get() = _navigateToDetail

    fun onSearchClicked() {
        viewModelScope.launch {
            try {
                val apiResponse = GameApi.retrofitService.searchGame(searchText.value!!, gameApiKey,
                        "json","guid,name,deck,genres,platforms,image", "game", 100)
                Log.i("I/SGVM","Success:${apiResponse.numResults} items retrieved")
                _gameList.value = apiResponse.results
            } catch (e: Exception) {
                Log.i("I/SGVM", "Failure: ${e.message}")
            }
        }
    }

    fun onGameItemClicked(gameItem: GameItem) {
        _navigateToDetail.value = gameItem
    }

    fun onDetailNavigated() {
        _navigateToDetail.value = null
    }
}
package jzam.backlog.backlogtracker.searchgame

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import jzam.backlog.backlogtracker.R
import jzam.backlog.backlogtracker.databinding.FragmentSearchGameBinding


/*
 * Fragment to search for a game in an online database provided by the GiantBomb API
 */
class SearchGameFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {

        val GB_API_KEY = this.getString(R.string.giantbomb_api_key)

        val binding: FragmentSearchGameBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_search_game, container, false)

        val viewModelFactory = SearchGameViewModelFactory(GB_API_KEY)

        val searchGameViewModel =
                ViewModelProvider(
                        this, viewModelFactory).get(SearchGameViewModel::class.java)

        binding.searchGameViewModel = searchGameViewModel
        binding.lifecycleOwner = this

        val adapter = SearchGameAdapter(SearchGameListener { guid ->
            searchGameViewModel.onGameItemClicked(guid)
        })

        binding.searchGameList.adapter = adapter

        searchGameViewModel.gameList.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        searchGameViewModel.navigateToDetail.observe(viewLifecycleOwner, { item ->
            item?.let {
                this.findNavController().navigate(
                    SearchGameFragmentDirections.actionSearchGameFragmentToSearchGameDetailFragment(item))
                searchGameViewModel.onDetailNavigated()
            }
        })

        return binding.root
    }
}
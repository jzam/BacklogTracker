package jzam.backlog.backlogtracker.searchgamedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import jzam.backlog.backlogtracker.R
import jzam.backlog.backlogtracker.database.BacklogDatabase
import jzam.backlog.backlogtracker.databinding.FragmentSearchGameDetailBinding

class SearchGameDetailFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        val binding: FragmentSearchGameDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search_game_detail, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = SearchGameDetailFragmentArgs.fromBundle(requireArguments())

        val dataSource = BacklogDatabase.getInstance(application).backlogDatabaseDao
        val viewModelFactory = SearchGameDetailViewModelFactory(arguments.gameItem, dataSource)

        val searchGameDetailViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(SearchGameDetailViewModel::class.java)

        binding.searchGameDetailViewModel= searchGameDetailViewModel
        binding.lifecycleOwner = this

        searchGameDetailViewModel.navigateToSearch.observe(viewLifecycleOwner, {
            it?.let {
                if (!this.findNavController().popBackStack()) {
                    Log.i("I/SGDF", "Popping back stack failed somehow...")
                    SearchGameDetailFragmentDirections.actionSearchGameDetailFragmentToSearchGameFragment()
                }
                searchGameDetailViewModel.onSearchNavigated()
                if (it.isNotEmpty()) {
                    Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
                }
            }
        })

        return binding.root
    }
}
package jzam.backlog.backlogtracker.backlogtracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import jzam.backlog.backlogtracker.R
import jzam.backlog.backlogtracker.databinding.FragmentBacklogTrackerBinding
import jzam.backlog.backlogtracker.database.BacklogDatabase
/*
 *  Fragment to view list of backlog items
 */
class BacklogTrackerFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {

        val binding: FragmentBacklogTrackerBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_backlog_tracker, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = BacklogDatabase.getInstance(application).backlogDatabaseDao
        val viewModelFactory = BacklogTrackerViewModelFactory(dataSource)

        val backlogTrackerViewModel =
                ViewModelProvider(
                        this, viewModelFactory).get(BacklogTrackerViewModel::class.java)

        binding.backlogTrackerViewModel = backlogTrackerViewModel
        binding.lifecycleOwner = this

        val adapter = BacklogItemAdapter(BacklogItemListener { itemId ->
            backlogTrackerViewModel.onBacklogItemClicked(itemId)
        })

        binding.backlogList.adapter = adapter

        backlogTrackerViewModel.backlog.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        backlogTrackerViewModel.navigateToAddItem.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController().navigate(
                        BacklogTrackerFragmentDirections.actionBacklogTrackerFragmentToAddItemFragment())
                backlogTrackerViewModel.onAddItemNavigated()
            }
        })

        backlogTrackerViewModel.navigateToEditItem.observe(viewLifecycleOwner, { item ->
            item?.let {
                this.findNavController().navigate(
                        BacklogTrackerFragmentDirections.actionBacklogTrackerFragmentToEditItemFragment(item))
                backlogTrackerViewModel.onEditItemNavigated()
            }
        })

        backlogTrackerViewModel.navigateToSearch.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController().navigate(
                        BacklogTrackerFragmentDirections.actionBacklogTrackerFragmentToSearchGameFragment())
                backlogTrackerViewModel.onSearchNavigated()
            }
        })

        return binding.root
    }
}
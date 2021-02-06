package jzam.backlog.backlogtracker.additem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import jzam.backlog.backlogtracker.R
import jzam.backlog.backlogtracker.databinding.FragmentAddItemBinding
import jzam.backlog.backlogtracker.additem.AddItemViewModel
import jzam.backlog.backlogtracker.additem.AddItemViewModelFactory
import jzam.backlog.backlogtracker.database.BacklogDatabase
import jzam.backlog.backlogtracker.additem.AddItemFragmentDirections

/*
 *  Fragment to add a backlog item
 */
class AddItemFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding: FragmentAddItemBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_item, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = BacklogDatabase.getInstance(application).backlogDatabaseDao
        val viewModelFactory = AddItemViewModelFactory(dataSource)

        val addItemViewModel =
                ViewModelProvider(
                        this, viewModelFactory).get(AddItemViewModel::class.java)

        binding.addItemViewModel = addItemViewModel
        binding.lifecycleOwner = this


        addItemViewModel.navigateToBacklogTracker.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController().navigate(
                        AddItemFragmentDirections
                                .actionAddItemFragmentToBacklogTrackerFragment())
                addItemViewModel.onBacklogTrackerNavigated()
            }
        })

        return binding.root
    }
}
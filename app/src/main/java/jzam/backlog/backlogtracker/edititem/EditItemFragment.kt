package jzam.backlog.backlogtracker.edititem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import jzam.backlog.backlogtracker.R
import jzam.backlog.backlogtracker.database.BacklogDatabase
import jzam.backlog.backlogtracker.databinding.FragmentEditItemBinding

/*
 * Fragment to view and edit a backlog item
 */
class EditItemFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {

        val binding: FragmentEditItemBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_edit_item, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = EditItemFragmentArgs.fromBundle(requireArguments())

        val dataSource = BacklogDatabase.getInstance(application).backlogDatabaseDao
        val viewModelFactory = EditItemViewModelFactory(arguments.backlogItemKey, dataSource)

        val editItemViewModel =
                ViewModelProvider(
                        this, viewModelFactory).get(EditItemViewModel::class.java)

        binding.editItemViewModel = editItemViewModel
        binding.lifecycleOwner = this


        editItemViewModel.navigateToBacklogTracker.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController().navigate(
                        EditItemFragmentDirections.actionEditItemFragmentToBacklogTrackerFragment())
                editItemViewModel.onBacklogTrackerNavigated()
            }
        })

        return binding.root
    }
}
package jzam.backlog.backlogtracker.backlogtracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jzam.backlog.backlogtracker.database.BacklogItem
import jzam.backlog.backlogtracker.databinding.ListBacklogItemBinding

class BacklogItemAdapter(val clickListener: BacklogItemListener) :
        ListAdapter<BacklogItem, BacklogItemAdapter.ViewHolder>(BacklogItemDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListBacklogItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BacklogItem, clickListener: BacklogItemListener) {
            binding.backlogItem = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListBacklogItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class BacklogItemDiffCallback : DiffUtil.ItemCallback<BacklogItem>() {

    override fun areItemsTheSame(oldItem: BacklogItem, newItem: BacklogItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }


    override fun areContentsTheSame(oldItem: BacklogItem, newItem: BacklogItem): Boolean {
        return oldItem == newItem
    }
}

class BacklogItemListener(val clickListener: (itemId: Long) -> Unit) {
    fun onClick(item: BacklogItem) = clickListener(item.itemId)
}


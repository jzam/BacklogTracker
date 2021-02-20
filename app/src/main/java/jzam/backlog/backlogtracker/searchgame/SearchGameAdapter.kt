package jzam.backlog.backlogtracker.searchgame

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jzam.backlog.backlogtracker.databinding.ListSearchGameItemBinding
import jzam.backlog.backlogtracker.network.GameItem

class SearchGameAdapter(val clickListener: SearchGameListener) :
        ListAdapter<GameItem, SearchGameAdapter.ViewHolder>(SearchGameDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListSearchGameItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GameItem, clickListener: SearchGameListener) {
            binding.gameItem = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListSearchGameItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class SearchGameDiffCallback : DiffUtil.ItemCallback<GameItem>() {

    override fun areItemsTheSame(oldItem: GameItem, newItem: GameItem): Boolean {
        return oldItem.guid == newItem.guid
    }


    override fun areContentsTheSame(oldItem: GameItem, newItem: GameItem): Boolean {
        return oldItem == newItem
    }
}

class SearchGameListener(val clickListener: (gameItem: GameItem) -> Unit) {
    fun onClick(item: GameItem) = clickListener(item)
}

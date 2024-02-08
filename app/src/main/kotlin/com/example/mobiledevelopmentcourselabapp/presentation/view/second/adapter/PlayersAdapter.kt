package com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.ItemAdCardBinding
import com.example.mobiledevelopmentcourselabapp.databinding.ItemPlayerBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.AdUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.ItemUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.PlayerUiModel

class PlayersAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ItemUiModel> = arrayListOf()

    fun updateItems(newItems: List<ItemUiModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == PLAYER_ID) {
            val binding =
                ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            PlayerHolder(binding)
        } else {
            val binding =
                ItemAdCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AdHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (item is PlayerUiModel && holder is PlayerHolder) {
            holder.bind(item)

            holder.setOnClickListener {
                item.isExpanded = !item.isExpanded
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is PlayerUiModel -> PLAYER_ID
            AdUiModel -> AD_ID
            else -> AD_ID
        }
    }

    class PlayerHolder(private val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(player: PlayerUiModel) {
            binding.name.text = player.name
            binding.number.text = player.number.toString()

            binding.additionalFields.isVisible = player.isExpanded

            binding.age.text = itemView.context.resources.getString(
                R.string.age_pattern,
                player.age,
                itemView.context.resources.getQuantityText(R.plurals.age, player.age)
            ) // первый подход

            binding.playerPosition.text = player.formattedPosition
            binding.team.text = player.formattedTeam // второй подход

            Glide
                .with(itemView)
                .load(player.photoUrl)
                .into(binding.icon)
        }

        fun setOnClickListener(action: () -> Unit) {
            binding.root.setOnClickListener { action.invoke() }
        }
    }

    class AdHolder(binding: ItemAdCardBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        const val PLAYER_ID = 0
        const val AD_ID = 1
    }
}
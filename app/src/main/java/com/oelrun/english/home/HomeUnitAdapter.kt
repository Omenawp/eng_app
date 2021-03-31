package com.oelrun.english.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.english.R
import com.oelrun.english.database.UnitProgress
import com.oelrun.english.databinding.ListItemHomeBinding


class HomeUnitAdapter(val clickListener: UnitListener):
        ListAdapter<UnitProgress, HomeUnitAdapter.ViewHolder>(UnitProgressDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ListItemHomeBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: UnitListener, item: UnitProgress) {
            binding.unit = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemHomeBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class UnitProgressDiffCallback : DiffUtil.ItemCallback<UnitProgress>() {
    override fun areItemsTheSame(oldItem: UnitProgress, newItem: UnitProgress): Boolean {
        return oldItem.unitId == newItem.unitId
    }

    override fun areContentsTheSame(oldItem: UnitProgress, newItem: UnitProgress): Boolean {
        return oldItem == newItem
    }
}


class UnitListener(val clickListener: (unitId: Int) -> Unit) {
    fun onClick(unit: UnitProgress) = clickListener(unit.unitId)
}


@BindingAdapter("isCompleted")
fun Button.isUnitCompleted(item: UnitProgress?) {
    item?.let {
        val background = if(it.game_1 && it.game_2)
            R.drawable.main_item_star
        else
            R.drawable.main_item

        setBackgroundResource(background)
    }
}
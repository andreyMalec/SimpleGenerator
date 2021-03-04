package com.malec.simplegenerator.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.malec.simplegenerator.databinding.ItemNumberBinding
import com.malec.simplegenerator.model.Number

class NumberAdapter : PagedListAdapter<Number, NumberAdapter.NumberItemViewHolder>(diffCallback) {
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Number>() {
            override fun areItemsTheSame(oldItem: Number, newItem: Number): Boolean {
                return oldItem.data == newItem.data
            }

            override fun areContentsTheSame(oldItem: Number, newItem: Number): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemNumberBinding = ItemNumberBinding.inflate(inflater, parent, false)
        return NumberItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NumberItemViewHolder, position: Int) {
        val number = getItem(position)

        holder.binding?.number = number
    }

    inner class NumberItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemNumberBinding? = DataBindingUtil.bind(view)
    }
}
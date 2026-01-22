package com.example.rickmortyapp.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortyapp.databinding.CharacterHolderBinding
import com.example.rickmortyapp.domain.models.Character
import com.example.rickmortyapp.presentation.activity.MainActivity

class CharacterAdapter(private val onClickListener: MainActivity) :
    ListAdapter<Character.Result, CharacterAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            CharacterHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: CharacterAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: CharacterHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(characterResponse: Character.Result) {
            Log.d("ololo", "bind: image = ${characterResponse.image}")
        }
    }
}

interface OnClickListener {
    fun onClick(id: Int)
}

class DiffCallback : DiffUtil.ItemCallback<Character.Result>() {
    override fun areItemsTheSame(oldItem: Character.Result, newItem: Character.Result): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Character.Result, newItem: Character.Result): Boolean {
        return oldItem == newItem
    }
}
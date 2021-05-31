package com.bangtiray.movietv.ui.fragment.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity
import com.bangtiray.movietv.databinding.ItemDataBinding
import com.bangtiray.movietv.extension.loadFromUrl
import com.bangtiray.movietv.utils.ConstantValue

class MoviesAdapter(private val listener: ClickCallback) :
    PagedListAdapter<MoviesEntity, MoviesAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.mId == newItem.mId
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: MoviesEntity) {
            binding.ivThumbnail.loadFromUrl("${ConstantValue.imageUrl}${item.mPosterPath}")
            binding.movieTitle.text = item.mTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bindData(data)
            holder.itemView.setOnClickListener {
                listener.onItemClicked(data)
            }
        }
    }
}

interface ClickCallback {
    fun onItemClicked(data: MoviesEntity)
}

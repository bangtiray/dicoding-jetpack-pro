package com.bangtiray.movietv.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity
import com.bangtiray.movietv.databinding.ItemDataBinding
import com.bangtiray.movietv.extension.loadFromUrl
import com.bangtiray.movietv.utils.ConstantValue

class MovieAdapter internal constructor(private val listener: ClickListener) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val list = ArrayList<MoviesEntity>()

    internal fun setData(data: List<MoviesEntity>?) {
        if (data == null) return
        this.list.clear()
        this.list.addAll(data)
    }

    class ViewHolder(private val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root) {
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
        holder.bindData(list[position])
        holder.itemView.setOnClickListener {
            listener.onCLick(holder.adapterPosition, list[holder.adapterPosition].mId)
        }

    }

    override fun getItemCount() = list.size
}

internal interface ClickListener {
    fun onCLick(position: Int, movieId: Int)
}
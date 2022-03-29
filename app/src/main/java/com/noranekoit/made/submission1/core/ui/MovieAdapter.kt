package com.noranekoit.made.submission1.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noranekoit.made.submission1.BuildConfig
import com.noranekoit.made.submission1.R
import com.noranekoit.made.submission1.core.data.source.local.entity.MovieEntity
import com.noranekoit.made.submission1.databinding.ItemListMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {
    private var listData = ArrayList<MovieEntity>()
    var onItemClick: ((MovieEntity) -> Unit)? = null

    fun setData(newListData: List<MovieEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_movie, parent, false
            )
        )


    override fun onBindViewHolder(holder: MovieAdapter.ListViewHolder, position: Int) =
        holder.bind(listData[position])

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: MovieEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("${BuildConfig.BASE_URL_IMAGE}${data.imagePath}")
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemSubtitle.text = data.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }


}
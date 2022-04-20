package com.noranekoit.made.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.noranekoit.made.core.BuildConfig
import com.noranekoit.made.core.R
import com.noranekoit.made.core.databinding.ItemListMovieBinding
import com.noranekoit.made.core.domain.model.Moviem
import com.noranekoit.made.core.utils.DiffUtilHelper
import com.squareup.picasso.Picasso
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {
    private var listData = ArrayList<Moviem>()
    var onItemClick: ((Moviem) -> Unit)? = null

    fun setData(newListData: List<Moviem>?) {
        if (newListData == null) return
        val diffUtilHelper = DiffUtilHelper(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffUtilHelper)
        listData.clear()
        listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
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
        fun bind(data: Moviem) {
            with(binding) {
                Picasso.get()
                    .load("${BuildConfig.BASE_URL_IMAGE}${data.imagePath}")
                    .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                    .error(android.R.drawable.stat_notify_error)
                    .into(ivItemImage)

//                Glide.with(itemView.context)
//                    .load("${BuildConfig.BASE_URL_IMAGE}${data.imagePath}")
//                    .placeholder(android.R.drawable.progress_indeterminate_horizontal)
//                    .error(android.R.drawable.stat_notify_error)
//                    .into(ivItemImage)
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
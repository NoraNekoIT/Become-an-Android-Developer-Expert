package com.noranekoit.made.core.utils

import androidx.recyclerview.widget.DiffUtil
import com.noranekoit.made.core.domain.model.Moviem

class DiffUtilHelper(
    private val oldList: List<Moviem>,
    private val newList: List<Moviem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newItem = newList[newItemPosition]
        val oldItem = oldList[oldItemPosition]
        return compare(oldItem, newItem)
    }

    private fun compare( oldItem: Moviem,newItem: Moviem): Boolean {
        return (oldItem.id == newItem.id)
                && (oldItem.dateAiring == newItem.dateAiring)
                && (oldItem.description == newItem.description)
                && (oldItem.score == newItem.score)
                && (oldItem.imagePath == newItem.imagePath)
                && (oldItem.title == newItem.imagePath)
    }
}
package com.noranekoit.made.core.utils

import androidx.recyclerview.widget.DiffUtil
import com.noranekoit.made.core.domain.model.Moviem

class DiffUtilHelper(
    private val newList: List<Moviem>,
    private val oldList: List<Moviem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newItem = newList[newItemPosition]
        val oldItem = oldList[oldItemPosition]
        return compare(newItem, oldItem)
    }

    private fun compare(newItem: Moviem, oldItem: Moviem): Boolean {
        return (newItem.id == oldItem.id)
                && (newItem.dateAiring == oldItem.dateAiring)
                && (newItem.description == oldItem.description)
                && (newItem.score == oldItem.score)
                && (newItem.imagePath == oldItem.imagePath)
                && (newItem.title == oldItem.imagePath)
    }
}
package com.noranekoit.made.submission1.core.domain.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Moviem(
    val id: String,
    val title: String?,
    val description: String?,
    val dateAiring: String?,
    val score: String?,
    val imagePath: String?,
    var isFavorite: Boolean =false
): Parcelable

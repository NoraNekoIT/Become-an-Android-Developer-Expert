package com.noranekoit.made.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movieEntities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "dateAiring")
    val dateAiring: String?,

    @ColumnInfo(name = "score")
    val score: String?,

    @ColumnInfo(name = "imagePath")
    val imagePath: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
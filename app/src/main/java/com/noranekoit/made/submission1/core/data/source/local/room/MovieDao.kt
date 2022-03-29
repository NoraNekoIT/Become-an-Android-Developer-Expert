package com.noranekoit.made.submission1.core.data.source.local.room
import androidx.lifecycle.LiveData
import androidx.room.*
import com.noranekoit.made.submission1.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieEntities")
    fun getAllMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieEntities where isFavorite=1")
    fun getFavoriteMovie(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}
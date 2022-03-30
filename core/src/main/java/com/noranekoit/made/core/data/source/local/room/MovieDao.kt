package com.noranekoit.made.core.data.source.local.room
import androidx.room.*
import com.noranekoit.made.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieEntities")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieEntities where isFavorite=1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}
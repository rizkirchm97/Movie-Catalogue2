package com.rizki.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.rizki.moviecatalogue.data.source.local.entity.MovieEntity
import com.rizki.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface AppDao {

    @Query("SELECT * FROM moviesentities")
    fun getMoviesDao(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM moviesentities WHERE favourite = 1")
    fun getFavouriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshowsentities")
    fun getTvShowsDao(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowsentities WHERE favourite = 1")
    fun getFavouriteTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Transaction
    @Query("SELECT * FROM moviesentities WHERE moviesId = :movieId")
    fun getMovieById(movieId: String): LiveData<MovieEntity>

    @Transaction
    @Query("SELECT * FROM tvshowsentities WHERE tvShowId = :tvShowId")
    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}
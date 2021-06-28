package com.rizki.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rizki.moviecatalogue.data.source.local.entity.MovieEntity
import com.rizki.moviecatalogue.data.source.local.entity.TvShowEntity
import com.rizki.moviecatalogue.vo.Resource

interface AppDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieById(movieId: String): LiveData<Resource<MovieEntity>>
    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getTvShowById(tvShowId: String): LiveData<Resource<TvShowEntity>>
    fun getMovieFavourite(): LiveData<PagedList<MovieEntity>>
    fun getTvShowFavourite(): LiveData<PagedList<TvShowEntity>>
    fun setMovieFavourite(movie: MovieEntity, state: Boolean)
    fun setTvShowFavourite(tvSHow: TvShowEntity, state: Boolean)
}
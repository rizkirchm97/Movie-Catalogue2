package com.rizki.moviecatalogue.ui.favourite.favouritetvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rizki.moviecatalogue.data.source.AppRepository
import com.rizki.moviecatalogue.data.source.local.entity.TvShowEntity

class TvShowFavouriteViewModel(private val appRepository: AppRepository): ViewModel() {

    fun getFavTvShow(): LiveData<PagedList<TvShowEntity>> = appRepository.getTvShowFavourite()

    fun setFavTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.favourite
        appRepository.setTvShowFavourite(tvShowEntity, newState)
    }
}
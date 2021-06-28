package com.rizki.moviecatalogue.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rizki.moviecatalogue.data.source.AppRepository
import com.rizki.moviecatalogue.data.source.local.entity.TvShowEntity
import com.rizki.moviecatalogue.vo.Resource

class TvShowsViewModel(private val appRepository: AppRepository) : ViewModel() {
    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = appRepository.getAllTvShows()
}
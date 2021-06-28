package com.rizki.moviecatalogue.ui.detail.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.rizki.moviecatalogue.data.source.AppRepository
import com.rizki.moviecatalogue.data.source.local.entity.MovieEntity
import com.rizki.moviecatalogue.data.source.local.entity.TvShowEntity
import com.rizki.moviecatalogue.utils.DataDummy
import com.rizki.moviecatalogue.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowsViewModelTest {
    private lateinit var viewModel: DetailTvShowsViewModel
    private val dummyTvShows = DataDummy.generatedDummyDetailTvShows()
    private val tvShowId = dummyTvShows.tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailTvShowsViewModel(appRepository)
        viewModel.setSelectedTvShows(tvShowId)
    }

    @Test
    fun getTvShows() {
        val dummyDetail = Resource.success(DataDummy.generatedDummyDetailTvShows())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetail

        `when`(appRepository.getTvShowById(tvShowId)).thenReturn(tvShow)

        viewModel.tvShows.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetail)

        assertEquals(dummyDetail, viewModel.tvShows.value)
    }

    @Test
    fun setFavouriteTvShow() {
        val dummyDetailFav = Resource.success(DataDummy.generatedDummyDetailTvShows())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailFav

        `when`(appRepository.getTvShowById(tvShowId)).thenReturn(tvShow)
        viewModel.setFav()
        viewModel.tvShows.observeForever(tvShowObserver)

        verify(tvShowObserver).onChanged(tvShow.value)

        assertEquals(tvShow.value, viewModel.tvShows.value)
    }
}
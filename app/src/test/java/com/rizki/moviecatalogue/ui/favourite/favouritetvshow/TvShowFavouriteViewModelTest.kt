package com.rizki.moviecatalogue.ui.favourite.favouritetvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.rizki.moviecatalogue.data.source.AppRepository
import com.rizki.moviecatalogue.data.source.local.entity.TvShowEntity
import com.rizki.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowFavouriteViewModelTest {
    private lateinit var viewModel: TvShowFavouriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowFavouriteViewModel(appRepository)
    }

    @Test
    fun getFavTvShow() {
        val dummyFavTvShow = pagedList
        `when`(dummyFavTvShow.size).thenReturn(10)
        val favTvShow = MutableLiveData<PagedList<TvShowEntity>>()
        favTvShow.value = dummyFavTvShow

        `when`(appRepository.getTvShowFavourite()).thenReturn(favTvShow)
        val favTvShowEntities = viewModel.getFavTvShow().value
        verify(appRepository).getTvShowFavourite()
        assertNotNull(favTvShowEntities)
        assertEquals(10, favTvShowEntities?.size)

        viewModel.getFavTvShow().observeForever(observer)
        verify(observer).onChanged(dummyFavTvShow)
    }
}
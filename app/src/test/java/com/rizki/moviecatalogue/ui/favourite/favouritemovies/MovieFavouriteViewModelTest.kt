package com.rizki.moviecatalogue.ui.favourite.favouritemovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.rizki.moviecatalogue.data.source.AppRepository
import com.rizki.moviecatalogue.data.source.local.entity.MovieEntity
import com.rizki.moviecatalogue.utils.DataDummy
import com.rizki.moviecatalogue.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MovieFavouriteViewModelTest {
    private lateinit var viewModel: MovieFavouriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieFavouriteViewModel(appRepository)
    }

    @Test
    fun getFavMovie() {
        val dummyFavMovie = pagedList
        `when`(dummyFavMovie.size).thenReturn(10)
        val favMovie = MutableLiveData<PagedList<MovieEntity>>()
        favMovie.value = dummyFavMovie

        `when`(appRepository.getMovieFavourite()).thenReturn(favMovie)
        val favMovieEntities = viewModel.getFavMovie().value
        verify(appRepository).getMovieFavourite()
        assertNotNull(favMovieEntities)
        assertEquals(10, favMovieEntities?.size)

        viewModel.getFavMovie().observeForever(observer)
        verify(observer).onChanged(dummyFavMovie)
    }
}
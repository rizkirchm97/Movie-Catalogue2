package com.rizki.moviecatalogue.ui.detail.movies

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
class DetailMoviesViewModelTest {

    private lateinit var viewModel: DetailMoviesViewModel
    private val dummyMovies = DataDummy.generateDummyDetailMovies()
    private val movieId = dummyMovies.moviesId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>


    @Before
    fun setUp(){
        viewModel = DetailMoviesViewModel(appRepository)
        viewModel.setSelectedMovies(movieId)
    }

    @Test
    fun getMovies() {
        val movies = MutableLiveData<Resource<MovieEntity>>()
        movies.value = Resource.success(DataDummy.generateDummyDetailMovies())

        `when`(appRepository.getMovieById(movieId)).thenReturn(movies)

        viewModel.movie.observeForever(movieObserver)
        verify(movieObserver).onChanged(movies.value)

        assertEquals(movies.value, viewModel.movie.value)
    }

    @Test
    fun setFavouriteMovie() {
        val dummyDetailFav = Resource.success(DataDummy.generateDummyDetailMovies())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailFav

        `when`(appRepository.getMovieById(movieId)).thenReturn(movie)
        viewModel.setFav()
        viewModel.movie.observeForever(movieObserver)

        verify(movieObserver).onChanged(movie.value)

        assertEquals(dummyDetailFav, viewModel.movie.value)
    }
}
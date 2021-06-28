package com.rizki.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.rizki.moviecatalogue.data.source.local.LocalDataSource
import com.rizki.moviecatalogue.data.source.local.entity.MovieEntity
import com.rizki.moviecatalogue.data.source.local.entity.TvShowEntity
import com.rizki.moviecatalogue.data.source.remote.RemoteDataSource
import com.rizki.moviecatalogue.utils.AppExecutors
import com.rizki.moviecatalogue.utils.DataDummy
import com.rizki.moviecatalogue.utils.LiveDataTestUtil
import com.rizki.moviecatalogue.utils.PagedListUtils
import com.rizki.moviecatalogue.vo.Resource
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class AppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val appRepository = FakeAppRepository(remote, local, appExecutors)

    private val movieResponse = DataDummy.generateRemoteDummyMovies()
    private val tvShowResponse = DataDummy.generatedRemoteDummyTvShows()
    private val movieId = movieResponse[0].moviesId
    private val tvShowId = tvShowResponse[0].tvShowId

    private val movieDetail = DataDummy.generateRemoteDummyDetailMovies()
    private val tvShowDetail = DataDummy.generatedRemoteDummyDetailTvShows()

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllLocalMovies()).thenReturn(dataSourceFactory)
        appRepository.getAllMovies()

        val moviesEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllLocalMovies()
        assertNotNull(moviesEntities.data)
        assertEquals(movieResponse.size.toLong(), moviesEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieById() {
        val dummyMovieById = MutableLiveData<MovieEntity>()
        dummyMovieById.value = DataDummy.generateDummyDetailMovies()

        `when`(local.getMovieById(movieId)).thenReturn(dummyMovieById)

        val moviesEntities = LiveDataTestUtil.getValue(appRepository.getMovieById(movieId))
        verify(local).getMovieById(movieId)
        assertNotNull(moviesEntities.data)
        assertEquals(movieDetail.moviesId, moviesEntities.data?.moviesId)
    }

    @Test
    fun getFavouriteMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavouriteMovie()).thenReturn(dataSourceFactory)
        appRepository.getMovieFavourite()

        val movieFavEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavouriteMovie()
        assertNotNull(movieFavEntities)
        assertEquals(movieResponse.size.toLong(), movieFavEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllLocalTvShows()).thenReturn(dataSourceFactory)
        appRepository.getAllTvShows()

        val tvShowEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generatedDummyTvShows()))
        verify(local).getAllLocalTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShowById() {
        val dummyTvShowById = MutableLiveData<TvShowEntity>()
        dummyTvShowById.value = DataDummy.generatedDummyDetailTvShows()

        `when`(local.getTvShowById(tvShowId)).thenReturn(dummyTvShowById)

        val tvShowEntities = LiveDataTestUtil.getValue(appRepository.getTvShowById(tvShowId))
        verify(local).getTvShowById(tvShowId)
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowDetail.tvShowId, tvShowEntities.data?.tvShowId)

    }
    @Test
    fun getFavouriteTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavouriteTvShow()).thenReturn(dataSourceFactory)
        appRepository.getTvShowFavourite()

        val tvShowFavEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generatedDummyTvShows()))
        verify(local).getFavouriteTvShow()
        assertNotNull(tvShowFavEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowFavEntities.data?.size?.toLong())
    }
}
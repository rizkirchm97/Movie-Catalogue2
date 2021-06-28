package com.rizki.moviecatalogue.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizki.moviecatalogue.databinding.FragmentMoviesBinding
import com.rizki.moviecatalogue.viewmodel.ViewModelFactory
import com.rizki.moviecatalogue.vo.Status

class MoviesFragment : Fragment() {

    private lateinit var moviesBinding: FragmentMoviesBinding
    private lateinit var movieViewModel: MoviesViewModel
    private lateinit var adapterMovie: MoviesFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        moviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return moviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            movieViewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

            adapterMovie = MoviesFragmentAdapter()
            movieViewModel.getMovies().observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> moviesBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            moviesBinding.progressBar.visibility = View.GONE
                            adapterMovie.submitList(movie.data)
                        }
                        Status.ERROR -> {
                            moviesBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(moviesBinding.rvMovies) {
                this.layoutManager = LinearLayoutManager(context)
                this.setHasFixedSize(true)
                this.adapter = adapterMovie
            }
        }
    }

}
package com.rizki.moviecatalogue.ui.favourite.favouritetvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rizki.moviecatalogue.R
import com.rizki.moviecatalogue.data.source.local.entity.TvShowEntity
import com.rizki.moviecatalogue.databinding.FragmentTvShowFavouriteBinding
import com.rizki.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFavouriteFragment : Fragment(), TvShowFavouriteFragmentCallback {
    private lateinit var viewModel: TvShowFavouriteViewModel
    private lateinit var adapterFavTv: TvShowFavouriteAdapter

    private lateinit var binding: FragmentTvShowFavouriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvShowFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding.rvFavTvShows)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowFavouriteViewModel::class.java]

            adapterFavTv = TvShowFavouriteAdapter(this)
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getFavTvShow().observe(viewLifecycleOwner, { favTvShow ->
                binding.progressBar.visibility = View.GONE
                adapterFavTv.submitList(favTvShow)
            })

            with(binding.rvFavTvShows) {
                this.layoutManager = LinearLayoutManager(context)
                this.setHasFixedSize(true)
                this.adapter = adapterFavTv
            }
        }
    }

    override fun onShareClick(movie: TvShowEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan Aplikasi ini sekarang.")
                .setText(resources.getString(R.string.share_text, movie.title))
                .startChooser()
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tvShowEntity = adapterFavTv.getSwipedData(swipedPosition)
                tvShowEntity?.let { viewModel.setFavTvShow(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    tvShowEntity?.let { viewModel.setFavTvShow(it) }
                }
                snackbar.show()
            }
        }
    })

}
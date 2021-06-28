package com.rizki.moviecatalogue.ui.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizki.moviecatalogue.R
import com.rizki.moviecatalogue.data.source.local.entity.TvShowEntity
import com.rizki.moviecatalogue.databinding.ItemsTvShowsBinding
import com.rizki.moviecatalogue.ui.detail.tvshows.DetailTvShowsActivity

class TvShowsFragmentAdapter: PagedListAdapter<TvShowEntity, TvShowsFragmentAdapter.TvShowsViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val itemsTvShowsBinding =
            ItemsTvShowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemsTvShowsBinding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val tvShows = getItem(position)
        if (tvShows != null) {
            holder.bind(tvShows)
        }
    }


    inner class TvShowsViewHolder(private val binding: ItemsTvShowsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShows: TvShowEntity) {
            with(binding) {
                tvItemTitleTvShows.text = tvShows.title
                tvNetworkTvShows.text = tvShows.network
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailTvShowsActivity::class.java)
                    intent.putExtra(DetailTvShowsActivity.EXTRA_TVSHOWS, tvShows.tvShowId)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(tvShows.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPosterTvShows)
            }
        }

    }

}
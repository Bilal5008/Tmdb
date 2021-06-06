package com.brainx.spotify.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.brainx.spotify.R
import com.brainx.spotify.data.models.Artist
import com.brainx.spotify.data.models.listResponse.BaseListResponse
import com.brainx.spotify.databinding.ArtistRecyclerviewItemBinding
import com.digi.tmdb.R
import com.digi.tmdb.feature.movielist.listResponse.BaseListResponse

class ArtistListAdapter : RecyclerView.Adapter<ArtistListAdapter.ArtistViewHolder>() {

    var artistListData = ArrayList<BaseListResponse?>()
    var filterArtistListData = ArrayList<BaseListResponse?>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtistViewHolder {

        return ArtistViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.artist_recyclerview_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bindHolder(filterArtistListData[position])
    }

    override fun getItemCount(): Int {
        return if (filterArtistListData.isNullOrEmpty())
            0
        else
            filterArtistListData.size
    }

    inner class ArtistViewHolder(private val binding: ArtistRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindHolder(artistObject: BaseListResponse?) {
            binding.apply {
                movie = artistObject
                adapter = this@ArtistListAdapter
                executePendingBindings()
            }
        }
    }

    fun getFilterList(query: String): ArrayList<BaseListResponse?> {
        if (query.isNullOrEmpty()) {
            filterArtistListData = artistListData
        }

        filterArtistListData = ArrayList(artistListData.filter { it?.title?.toLowerCase()?.contains(query.toLowerCase()) == true })
        notifyDataSetChanged()
        return filterArtistListData
    }
}
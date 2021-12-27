package com.digi.tmdb.feature.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.digi.tmdb.R
import com.digi.tmdb.databinding.MovieListRecyclerviewItemBinding
import com.digi.tmdb.feature.movielist.RecyclerViewClickListener
import com.digi.tmdb.feature.movielist.listResponse.BaseListResponse

class MoviesAdapter(private val listener: RecyclerViewClickListener) :
  RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

  var artistListData = ArrayList<BaseListResponse?>()
  var filterArtistListData = ArrayList<BaseListResponse?>()


  override fun getItemCount() = filterArtistListData.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    MoviesViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.movie_list_recyclerview_item,
        parent,
        false
      )
    )

  override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
    holder.recyclerviewMovieBinding.movie = filterArtistListData[position]
    holder.recyclerviewMovieBinding.root.setOnClickListener {
      listener.onRecyclerViewItemClick(
        holder.recyclerviewMovieBinding.root, filterArtistListData[position]
      )
    }
  }


  inner class MoviesViewHolder(
    val recyclerviewMovieBinding: MovieListRecyclerviewItemBinding
  ) : RecyclerView.ViewHolder(recyclerviewMovieBinding.root)


  fun getFilterList(query: String): ArrayList<BaseListResponse?> {
    if (query.isEmpty()) {
      filterArtistListData = artistListData
    }

    filterArtistListData = ArrayList(artistListData.filter {
      it?.title?.toLowerCase()?.contains(query.toLowerCase()) == true
    })
    notifyDataSetChanged()
    return filterArtistListData
  }

}
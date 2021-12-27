package com.digi.tmdb.feature.movielist

import android.view.View
import com.digi.tmdb.feature.movielist.listResponse.BaseListResponse


interface RecyclerViewClickListener {
  fun onRecyclerViewItemClick(view: View, movie: BaseListResponse?)
}
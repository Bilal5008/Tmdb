package com.digi.tmdb.feature.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.digi.tmdb.R
import com.digi.tmdb.base.factory.GlobalViewModelFactory
import com.digi.tmdb.databinding.FragmentMovieListBinding
import com.digi.tmdb.feature.movielist.adapter.MovieListAdapter
import com.digi.tmdb.feature.movielist.viewmodel.MovieListViewModel
import com.digi.tmdb.retrofit.Filter
import com.digi.tmdb.retrofit.RetroInstance
import com.digi.tmdb.utils.AppConstants
import com.digi.tmdb.utils.internetconnectivity.ConnectionLiveData


class MovieListFragment : Fragment(), LifecycleOwner {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var movieViewModel: MovieListViewModel
    private var query: String = ""
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        movieViewModel =
            ViewModelProvider(this, GlobalViewModelFactory(ApiHelper(RetroInstance.apiService))).get(
                MovieListViewModel::class.java
            )

        return binding.root
    }

    private fun loadAPIData(query: String) {
        if (query.isEmpty()) return
        movieViewModel.prepareMovieRepo(query)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setSearchBar()
        createObserver()


        val connectionLiveData =
            ConnectionLiveData(context)
        connectionLiveData.observe(viewLifecycleOwner,
            { connection ->
                if (connection?.isConnected == true) {
                    when (connection.type) {
                        AppConstants.WIFI_DATA ->
                            loadAPIData(Filter.POPULAR.filter)
                        AppConstants.CELL_DATA ->
                            loadAPIData(Filter.POPULAR.filter)
                    }
                } else {
                    binding.rvArtist.isInvisible
                    movieListAdapter.artistListData.clear()
                    movieListAdapter.notifyDataSetChanged()

                }
            })
    }

    private fun setAdapter() {
        binding.rvArtist.apply {
            movieListAdapter = MovieListAdapter()
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }

    private fun setSearchBar() {
        binding.etArtistIds.apply {
            doAfterTextChanged {
                query = it.toString()
                movieListAdapter.getFilterList(query)
            }
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    loadAPIData(query)
                    return@setOnEditorActionListener true
                }
                false
            }
        }
    }

    private fun createObserver() {
        movieViewModel.apply { binding.movieListViewModel = this
            movieViewModel.movieLiveData.observe(viewLifecycleOwner, Observer {



                if (it.results.isNullOrEmpty()) {


                    binding.apply {
                        tvArtistItemsTitle.isVisible
                        rvArtist.isVisible
                    }

                    binding.apply {
                        tvArtistItemsTitle.isInvisible
                        rvArtist.isInvisible
                    }
                } else {
                    movieListAdapter.apply {
                        artistListData = ArrayList(it.results)
                        filterArtistListData = artistListData
                        notifyDataSetChanged()
                    }


                }
            })
        }
    }


}
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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.digi.tmdb.R
import com.digi.tmdb.base.factory.GlobalViewModelFactory
import com.digi.tmdb.databinding.FragmentMovieListBinding
import com.digi.tmdb.feature.movielist.adapter.MoviesAdapter
import com.digi.tmdb.feature.movielist.listResponse.BaseListResponse
import com.digi.tmdb.feature.movielist.movieListManager.ListApiManager
import com.digi.tmdb.feature.movielist.movieListManager.NowPlayingMovies
import com.digi.tmdb.feature.movielist.movieListManager.PopularMovies
import com.digi.tmdb.feature.movielist.movieListManager.UpComingMovies
import com.digi.tmdb.feature.movielist.viewmodel.MovieListViewModel
import com.digi.tmdb.utils.AppConstants
import com.digi.tmdb.utils.internetconnectivity.ConnectionLiveData


class MovieListFragment : Fragment(), LifecycleOwner, RecyclerViewClickListener {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var movieViewModel: MovieListViewModel
    private var query: String = ""
    private lateinit var movieListAdapter: MoviesAdapter
    private lateinit var listApiManager: ListApiManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        movieViewModel =
            ViewModelProvider(this, GlobalViewModelFactory()).get(MovieListViewModel::class.java)
        listApiManager = ListApiManager()
        return binding.root
    }

    private fun loadAPIData() {
        listApiManager.popularMovies(PopularMovies(movieViewModel))
        listApiManager.nowPlayingMovies(NowPlayingMovies(movieViewModel))
        listApiManager.upCommingMovies(UpComingMovies(movieViewModel))

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
                            loadAPIData()
                        AppConstants.CELL_DATA ->
                            loadAPIData()
                    }
                } else {
                    binding.rvMovieList.isInvisible
                    movieListAdapter.artistListData.clear()
                    movieListAdapter.notifyDataSetChanged()

                }
            })
    }

    private fun setAdapter() {
        movieListAdapter = MoviesAdapter(this)
        binding.rvMovieList.apply {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }

    private fun setSearchBar() {

        binding.searchLayoutId.searchBar.apply {
            doAfterTextChanged {
                query = it.toString()
                movieListAdapter.getFilterList(query)
            }

            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    loadAPIData()
                    return@setOnEditorActionListener true
                }
                false
            }
        }
        binding.searchLayoutId.searchBar.text.clear()
    }

    private fun createObserver() {
        movieViewModel.apply {
            binding.movieListViewModel = this
            movieViewModel.movieListLiveData.observe(viewLifecycleOwner, Observer {


                if (it.results.isNullOrEmpty()) {


                    binding.apply {
                        tvArtistItemsTitle.isVisible
                        rvMovieList.isVisible
                    }

                    binding.apply {
                        tvArtistItemsTitle.isInvisible
                        rvMovieList.isInvisible
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

    override fun onRecyclerViewItemClick(view: View, movie: BaseListResponse?) {

        findNavController().navigate(
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(
                movie!!
            )
        )


    }


}
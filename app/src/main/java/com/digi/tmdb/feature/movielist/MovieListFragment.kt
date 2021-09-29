package com.digi.tmdb.feature.movielist

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.digi.tmdb.R
import com.digi.tmdb.base.BaseFragment
import com.digi.tmdb.databinding.FragmentMovieListBinding
import com.digi.tmdb.feature.movielist.adapter.MoviesAdapter
import com.digi.tmdb.feature.movielist.listResponse.BaseListResponse
import com.digi.tmdb.feature.movielist.movieListManager.ListApiManager
import com.digi.tmdb.feature.movielist.movieListManager.PopularMovies
import com.digi.tmdb.feature.movielist.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMovieListBinding, MovieListViewModel>(), LifecycleOwner, RecyclerViewClickListener {
    override val viewModel: MovieListViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_movie_list
    private var query: String = ""
    private lateinit var movieListAdapter: MoviesAdapter
    private lateinit var listApiManager: ListApiManager


    private fun loadAPIData() {
        listApiManager.popularMovies(PopularMovies(viewModel))
//        listApiManager.nowPlayingMovies(NowPlayingMovies(movieViewModel))
//        listApiManager.upCommingMovies(UpComingMovies(movieViewModel))

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listApiManager = ListApiManager()
        setAdapter()
        setSearchBar()
        createObserver()
        if (viewModel.mutableMovieList.value?.size == 0 || viewModel.mutableMovieList.value == null) {
            loadAPIData()
        }
    }

    private fun setAdapter() {
        movieListAdapter =
            MoviesAdapter(this)
        viewBinding.rvMovieList.apply {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }


    private fun setSearchBar() {
        viewBinding.searchLayoutId.searchBar.doAfterTextChanged {
            viewModel.mquery.value = it.toString()
        }


    }

    private fun createObserver() {
        viewModel.mutableMovieList.observe(viewLifecycleOwner, {
            movieListAdapter.apply {
                artistListData = it as ArrayList<BaseListResponse?>
                filterArtistListData = artistListData

                notifyDataSetChanged()
            }
        })
        viewModel.mquery.observe(viewLifecycleOwner, { filterString ->

            movieListAdapter.getFilterList(filterString)

        })

    }


    override fun onRecyclerViewItemClick(view: View, movie: BaseListResponse?) {

        findNavController().navigate(
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(
                movie!!
            )
        )
    }

}
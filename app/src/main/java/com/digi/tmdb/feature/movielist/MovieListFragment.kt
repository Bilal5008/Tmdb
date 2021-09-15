package com.digi.tmdb.feature.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.digi.tmdb.R
import com.digi.tmdb.databinding.FragmentMovieListBinding
import com.digi.tmdb.feature.movielist.adapter.MoviesAdapter
import com.digi.tmdb.feature.movielist.listResponse.AllListResponse
import com.digi.tmdb.feature.movielist.listResponse.BaseListResponse
import com.digi.tmdb.feature.movielist.movieListManager.ListApiManager
import com.digi.tmdb.feature.movielist.movieListManager.PopularMovies
import com.digi.tmdb.feature.movielist.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(), LifecycleOwner, RecyclerViewClickListener {

    private lateinit var binding: FragmentMovieListBinding
    private val movieViewModel: MovieListViewModel by viewModels()
    private var query: String = ""
    private lateinit var movieListAdapter: MoviesAdapter
    private lateinit var listApiManager: ListApiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
//        movieViewModel =
//            ViewModelProvider(this, GlobalViewModelFactory()).get(MovieListViewModel::class.java)
        listApiManager = ListApiManager()
        return binding.root
    }

    private fun loadAPIData() {
        listApiManager.popularMovies(PopularMovies(movieViewModel))
//        listApiManager.nowPlayingMovies(NowPlayingMovies(movieViewModel))
//        listApiManager.upCommingMovies(UpComingMovies(movieViewModel))

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setSearchBar()
        createObserver()
        if (movieViewModel.mutableMovieList.value?.size == 0 || movieViewModel.mutableMovieList.value == null) {
            loadAPIData()
        }
    }

    private fun setAdapter() {
        movieListAdapter =
            MoviesAdapter(this)
        binding.rvMovieList.apply {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }


    private fun setSearchBar() {
        binding.searchLayoutId.searchBar.doAfterTextChanged {
            movieViewModel.mquery.value = it.toString()
        }


    }

    private fun createObserver() {
        movieViewModel.mutableMovieList.observe(viewLifecycleOwner, {
            movieListAdapter.apply {
                artistListData = it as ArrayList<BaseListResponse?>
                filterArtistListData = artistListData

                notifyDataSetChanged()
            }
        })
        movieViewModel.mquery.observe(viewLifecycleOwner, { filterString ->

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
package com.digi.tmdb.feature.movielist

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.digi.tmdb.R
import com.digi.tmdb.databinding.MovieListActivityBinding
import com.digi.tmdb.feature.adapter.MovieListAdapter
import com.digi.tmdb.feature.movielist.factory.ViewModelFactory
import com.digi.tmdb.feature.movielist.repo.MovieRepository
import com.digi.tmdb.feature.movielist.viewmodel.MovieViewModel
import com.digi.tmdb.retrofit.Filter

class MovieListActivity : AppCompatActivity() {

    private lateinit var movieListActivityBinding: MovieListActivityBinding

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var viewModelFactory: ViewModelFactory
    private var query: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieListActivityBinding = DataBindingUtil.setContentView(
            this,
            R.layout.movie_list_activity
        )



        movieViewModel = ViewModelProvider(this, viewModelFactory).get(
            MovieViewModel::class.java
        )
        setAdapter()
        setSearchBar()
//        createObserver()
        loadAPIData(Filter.POPULAR.filter)
    }

//    override fun layoutId() = R.layout.movie_list_activity
//
//    override fun initView(savedInstanceState: Bundle?) {
//
//        setAdapter()
//        setSearchBar()
//        createObserver()
//        loadAPIData(Filter.POPULAR.filter)
//
//    }

    private fun loadAPIData(query: String) {

        if (query.isEmpty()) return
        movieViewModel.getGithubAccount(query)


        movieViewModel.githubAccount.observe(this, Observer {
            Log.d("value", it.toString())
        })
    }


    private fun setAdapter() {
        movieListActivityBinding.rvArtist.apply {
            movieListAdapter = MovieListAdapter()
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(this@MovieListActivity)
            addItemDecoration(DividerItemDecoration(applicationContext, LinearLayout.VERTICAL))
        }
    }

    private fun setSearchBar() {
        movieListActivityBinding.etArtistIds.apply {

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


//    private fun createObserver() {
//
//        movieViewModel.apply {
//
//            movieListActivityBinding.artistViewModel = this
//
//            getMovieData(this@MovieListActivity).observe(
//                this@MovieListActivity,
//                Observer<AllListResponse> {
//                    if (it.results.isNullOrEmpty()) {
////                    showSnackBar(this@MainActivity, "Data Not found")
//                        movieListActivityBinding.apply {
//                            tvArtistItemsTitle.isInvisible
//                            rvArtist.isInvisible
//                        }
//
//                    } else {
//                        movieListAdapter.apply {
//                            artistListData = ArrayList(it.results)
//                            filterArtistListData = artistListData
//                            notifyDataSetChanged()
//                        }
//                        movieListActivityBinding.apply {
//                            tvArtistItemsTitle.isVisible
//                            rvArtist.isVisible
//                        }
//
//                    }
//                })
//        }
//    }


}
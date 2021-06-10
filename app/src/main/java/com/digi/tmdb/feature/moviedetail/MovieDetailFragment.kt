package com.digi.tmdb.feature.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.digi.tmdb.R
import com.digi.tmdb.base.factory.GlobalViewModelFactory
import com.digi.tmdb.databinding.FragmentMovieDetailBinding
import com.digi.tmdb.feature.moviedetail.viewmodel.MovieDetailViewModel


class MovieDetailFragment : Fragment() {
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var binding: FragmentMovieDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        movieDetailViewModel =
            ViewModelProvider(this, GlobalViewModelFactory()).get(
                MovieDetailViewModel::class.java
            )

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val message = arguments?.getParcelable<Movie>("movie")?.title
//        view.findViewById<TextView>(R.id.value).text = message.toString()

        createObserver()

        loadAPIData(337404)
    }


    private fun loadAPIData(query: Int) {

        movieDetailViewModel.prepareMovieDetailRepo(query)
    }

    private fun createObserver() {
        movieDetailViewModel.apply {
            binding.movieDetailViewModel = this
            movieDetailViewModel.movieDetailLiveData.observe(viewLifecycleOwner, Observer {
                it.run {

                }


            })
        }
    }

}
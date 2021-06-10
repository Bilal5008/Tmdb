package com.digi.tmdb.feature.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.digi.tmdb.R
import com.digi.tmdb.base.factory.GlobalViewModelFactory
import com.digi.tmdb.databinding.FragmentMovieDetailBinding
import com.digi.tmdb.feature.moviedetail.viewmodel.MovieDetailViewModel
import com.digi.tmdb.feature.movielist.listResponse.BaseListResponse
import com.squareup.picasso.Picasso


class MovieDetailFragment : Fragment() {

//    private val args: MovieDetailArgs by navArgs()

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
            ViewModelProvider(this, GlobalViewModelFactory()).get(MovieDetailViewModel::class.java)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addBackPress()


        val message = arguments?.getParcelable<BaseListResponse>("movie")?.id
        createObserver()
        message?.let { loadAPIData(it) }

    }

    private fun addBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner)
        {
            findNavController().navigate(
                MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieListFragment()
            )
        }
    }


    private fun loadAPIData(query: Int) {

        movieDetailViewModel.prepareMovieDetailRepo(query)
    }

    private fun createObserver() {
        movieDetailViewModel.apply {
            binding.movieDetailViewModel = this
            movieDetailViewModel.movieDetailLiveData.observe(viewLifecycleOwner, Observer {
                it.run {
                    binding.apply {

                        loadImage(posterImage, it.poster_path)
                        loadImage(backdropImage, it.backdrop_path)
                        tvDuration.text = it.runtime.toString()
                        tvOverview.text = it.overview
                        tvName.text = it.original_title
                        tvVote.text =
                            "Average Vote" + it.vote_average.toString() + "/" + "Total count" + it.vote_count.toString()
//                        movieRelatedTitle.text = it.original_title
                    }
                }


            })
        }
    }

    fun loadImage(view: ImageView, url: String) {
        Picasso.get().load("http://image.tmdb.org/t/p/w500$url").centerCrop().resize(100, 199)
            .placeholder(R.drawable.user_placeholder)
            .error(R.drawable.user_placeholder_error)

            .into(view);
    }

}
package com.bilal.banner.feature.bannerstatus

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.bilal.banner.R
import com.bilal.banner.base.factory.GlobalViewModelFactory
import com.bilal.banner.databinding.FragmentMovieListBinding
import com.bilal.banner.feature.bannerstatus.bannerapiManager.BannerApiManager
import com.bilal.banner.feature.bannerstatus.bannerapiManager.GetBannerStatus
import com.bilal.banner.feature.bannerstatus.viewmodel.BannerViewModel
import com.bilal.banner.networking.NetworkStatusChecker


class MovieListFragment : Fragment(), LifecycleOwner {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var movieViewModel: BannerViewModel
    private var query: String = ""
    private lateinit var listApiManager: BannerApiManager
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        movieViewModel =
            ViewModelProvider(this, GlobalViewModelFactory()).get(BannerViewModel::class.java)
        listApiManager = BannerApiManager()
        return binding.root
    }

    private fun loadAPIData() {
        listApiManager.popularMovies(GetBannerStatus(movieViewModel))

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createObserver()
        networkStatusChecker.performIfConnectedToInternet {

            loadAPIData()
        }

    }

    private fun createObserver() {
        movieViewModel.movieList.observe(viewLifecycleOwner, {
            println(it)
        })

        movieViewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

    }


}
package com.digi.tmdb.feature

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.digi.tmdb.R
import com.digi.tmdb.base.BaseFragment

class MovieListFragment : BaseFragment() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        handler =  Handler()
        handler.postDelayed({
            run {
                var navController: NavController = Navigation.findNavController(view)
                navController.navigate(R.id.action_movieListFragment_to_movieDetailFragment)
            }
        }, 4000)
        return view
    }

}
package com.digi.tmdb.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digi.tmdb.R

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }


}
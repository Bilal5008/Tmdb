package com.digi.tmdb.feature.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digi.tmdb.R
import com.digi.tmdb.base.BaseActivity

class MovieActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }
}
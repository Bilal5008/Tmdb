package com.digi.tmdb.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digi.tmdb.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}
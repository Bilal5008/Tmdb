package com.digi.tmdb

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.digi.tmdb.base.BaseActivity


class SplashActivity : BaseActivity() {
    var stop = false
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
    }

    override fun onPause() {
        super.onPause()
        stop = true
    }

    override fun onResume() {
        super.onResume()
        stop = false
//        Glide.with(this).asGif()
//                .load(R.drawable.splash_video)
//                .into(gifImageView!!)
        handler.postDelayed({
            if (!stop) {

            }
        }, 4000)

    }
}
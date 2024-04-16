package com.jimbonlemu.saul_el_episodio.view

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.jimbonlemu.saul_el_episodio.R
import com.jimbonlemu.saul_el_episodio.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySplashBinding.inflate(layoutInflater).apply {
            setContentView(root)
            ivSplashContentImg.startAnimation(
                AnimationUtils.loadAnimation(
                    this@SplashActivity,
                    R.anim.anim_fade_in_to_out
                )
            )
            setSplashAnimation()
        }
    }

    private fun setSplashAnimation(): MediaPlayer {
        return MediaPlayer.create(this@SplashActivity, R.raw.intro_saul).apply {
            setOnCompletionListener {
                @Suppress("DEPRECATION")
                Handler().postDelayed({
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }, SPLASH_SCREEN_DELAY)
            }
            start()
        }
    }

    companion object {
        private const val SPLASH_SCREEN_DELAY: Long = 0
    }
}

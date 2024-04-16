package com.jimbonlemu.saul_el_episodio

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.jimbonlemu.saul_el_episodio.databinding.ActivitySplashBinding


@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySplashBinding.inflate(layoutInflater).apply {
            setContentView(root)
            findViewById<ImageView>(R.id.iv_splash_content_img).startAnimation(
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
                Handler().postDelayed({
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }, 0)
            }
            start()
        }
    }
}

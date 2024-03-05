package com.jimbonlemu.saul_el_episodio

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView


@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var introPlayer: MediaPlayer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val imageContent :ImageView= findViewById(R.id.iv_splash_content_img)
        
        imageContent.startAnimation(
            AnimationUtils.loadAnimation(
                this@SplashActivity,
                R.anim.anim_fade_in_to_out
            )
        )

        introPlayer = MediaPlayer.create(this, R.raw.intro_saul)
        introPlayer.setOnCompletionListener {
            Handler().postDelayed({
               imageContent.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@SplashActivity,
                        R.anim.anim_zoom_in
                    )
                )
                startActivity(
                    Intent(this@SplashActivity, MainActivity::class.java)
                )
                finish()
            }, 0)
        }
        introPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        introPlayer.release()
    }
}

package com.jimbonlemu.saul_el_episodio

import android.graphics.Paint
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.TypefaceSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide

class DetailSeriesActivity : AppCompatActivity() {
    companion object {
        const val SERIES_ARGS = "series_args"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_series)

        val seriesArgs = if (Build.VERSION.SDK_INT >= 33) {

            intent.getParcelableExtra<Series>(SERIES_ARGS, Series::class.java)
        } else {
            intent.getParcelableExtra<Series>(SERIES_ARGS)
        }

        if (seriesArgs != null) {
            Glide.with(this).load(seriesArgs.image).into(findViewById(R.id.iv_detail_image_content))
            findViewById<TextView>(R.id.tv_season_layout_detail).text = seriesArgs.season
            findViewById<TextView>(R.id.tv_rating_layout_detail).text = seriesArgs.rating
            findViewById<TextView>(R.id.tv_episode_layout_detail).text = seriesArgs.episode
            findViewById<TextView>(R.id.tv_synopsis_layout_detail).text = seriesArgs.synopsis
        }
    }


}

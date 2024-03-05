package com.jimbonlemu.saul_el_episodio

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton


@Suppress("DEPRECATION")
class DetailSeriesActivity : BackActivity() {
    companion object {
        const val SERIES_ARGS = "series_args"
    }

    private lateinit var binding: DetailSeriesActivity

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_series)

        val seriesArgs = if (Build.VERSION.SDK_INT >= 33) {

            intent.getParcelableExtra<Series>(SERIES_ARGS, Series::class.java)
        } else {
            intent.getParcelableExtra<Series>(SERIES_ARGS)
        }

        if (seriesArgs != null) {
            supportActionBar?.title = seriesArgs.title
            Glide.with(this).load(seriesArgs.image).into(findViewById(R.id.iv_detail_image_content))
            findViewById<TextView>(R.id.tv_season_layout_detail).text = seriesArgs.season
            findViewById<TextView>(R.id.tv_rating_layout_detail).text =
                seriesArgs.rating + " /10 IMDB"
            findViewById<TextView>(R.id.tv_episode_layout_detail).text = seriesArgs.episode
            findViewById<TextView>(R.id.tv_synopsis_layout_detail).text = seriesArgs.synopsis

            findViewById<MaterialButton>(R.id.btn_detail_share).setOnClickListener {
                startActivity(
                    Intent.createChooser(
                        Intent(Intent.ACTION_SEND).putExtra(
                            Intent.EXTRA_TEXT,
                            "Title Series : ${seriesArgs.title}\n" +
                                    "Series Season : ${seriesArgs.season}\n" +
                                    "Rating Series : ${seriesArgs.rating}\n" +
                                    "Series Episode : ${seriesArgs.episode}\n" +
                                    "Synopsis Series : ${seriesArgs.synopsis}\n"
                        ).setType("text/plain"), null
                    )
                )
            }
        }


    }


}

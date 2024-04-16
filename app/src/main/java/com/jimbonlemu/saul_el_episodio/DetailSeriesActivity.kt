package com.jimbonlemu.saul_el_episodio

import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.bumptech.glide.Glide
import com.jimbonlemu.saul_el_episodio.databinding.ActivityDetailSeriesBinding

@Suppress("DEPRECATION")
class DetailSeriesActivity : BackActivity() {
    companion object {
        const val SERIES_ARGS = "series_args"
    }

    private val binding: ActivityDetailSeriesBinding by lazy {
        ActivityDetailSeriesBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            setContentView(root)

            val seriesArgs = if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra(SERIES_ARGS, Series::class.java)
            } else {
                intent.getParcelableExtra(SERIES_ARGS)
            }

            if (seriesArgs != null) {
                setValueSeries(seriesArgs)
                setActionButtonShare(seriesArgs)
            }
        }
    }

    private fun ActivityDetailSeriesBinding.setValueSeries(seriesArgs: Series) {
        supportActionBar?.title = seriesArgs.title
        Glide.with(this@DetailSeriesActivity).load(seriesArgs.image).into(ivDetailImageContent)
        with(include) {
            with(seriesArgs) {
                tvSeasonLayoutDetail.text = season
                "$rating /10 IMDB".also { tvRatingLayoutDetail.text = it }
                tvEpisodeLayoutDetail.text = episode
                tvSynopsisLayoutDetail.text = synopsis
            }
        }
    }

    private fun ActivityDetailSeriesBinding.setActionButtonShare(seriesArgs: Series) {
        btnDetailShare.setOnClickListener {
            with(seriesArgs) {
                startActivity(
                    Intent.createChooser(
                        Intent(Intent.ACTION_SEND).putExtra(
                            Intent.EXTRA_TEXT,
                            "Title Series : ${title}\n" +
                                    "Series Season : ${season}\n" +
                                    "Rating Series : ${rating}\n" +
                                    "Series Episode : ${episode}\n" +
                                    "Synopsis Series : ${synopsis}\n"
                        ).setType("text/plain"), null
                    )
                )
            }
        }
    }
}

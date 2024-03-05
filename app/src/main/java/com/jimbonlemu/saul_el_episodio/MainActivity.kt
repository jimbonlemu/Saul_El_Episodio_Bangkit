package com.jimbonlemu.saul_el_episodio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var rvSeries: RecyclerView
    private val listSeries = ArrayList<Series>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvSeries = findViewById(R.id.rv_list_of_series)
        rvSeries.setHasFixedSize(true)

        listSeries.addAll(getAllSeriesValue())
        recycleListSeries()

    }

    private fun getAllSeriesValue(): ArrayList<Series> {

        val listSeriesValue = ArrayList<Series>()
        for (i in resources.getStringArray(R.array.list_image).indices) {
            val seriesInPiece = Series(
                resources.getStringArray(R.array.list_image).getOrNull(i) ?: "",
                resources.getStringArray(R.array.list_title_episodes).getOrNull(i) ?: "",
                resources.getStringArray(R.array.list_rating).getOrNull(i) ?: "",
                resources.getStringArray(R.array.list_season).getOrNull(i) ?: "",
                resources.getStringArray(R.array.list_episode).getOrNull(i) ?: "",
                resources.getStringArray(R.array.list_synopsis).getOrNull(i) ?: "",
            )
            listSeriesValue.add(seriesInPiece)
        }


        return listSeriesValue

    }

    private fun recycleListSeries() {
        rvSeries.layoutManager = LinearLayoutManager(this)
        val listSeriesAdapter = ListSeriesAdapter(listSeries)
        rvSeries.adapter = listSeriesAdapter

        listSeriesAdapter.setOnItemClickCallback(object :
            ListSeriesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Series) {
                startActivity(
                    Intent(this@MainActivity, DetailSeriesActivity::class.java).putExtra(
                        DetailSeriesActivity.SERIES_ARGS,
                        data
                    )
                )
            }



        })

    }

}

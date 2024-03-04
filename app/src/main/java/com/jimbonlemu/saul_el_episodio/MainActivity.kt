package com.jimbonlemu.saul_el_episodio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
                getToast(value = data)
            }

        })

    }

    private fun getToast(value: Series) = Toast.makeText(this, "This series is " + value.title, Toast.LENGTH_SHORT).show()

}

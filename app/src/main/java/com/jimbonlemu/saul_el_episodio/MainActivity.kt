package com.jimbonlemu.saul_el_episodio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jimbonlemu.saul_el_episodio.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var rvSeries: RecyclerView
    private val listSeries = ArrayList<Series>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            rvSeries = rvListOfSeries
            rvSeries.setHasFixedSize(true)

            listSeries.addAll(getAllSeriesValue())
            recycleListSeries()
        }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

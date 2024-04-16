package com.jimbonlemu.saul_el_episodio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

@Suppress("DEPRECATION")
open class BackActivity(private val appBarTitle: String? = null) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appBarTitle?.let { setupAppBar(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupAppBar(title: String) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.back_icon)
            this.title = title
        }
    }
}

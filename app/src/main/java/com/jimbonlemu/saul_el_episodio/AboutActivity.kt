package com.jimbonlemu.saul_el_episodio

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class AboutActivity : BackActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.title = "About Developer"
        findViewById<MaterialButton>(R.id.btn_about_github).setOnClickListener {
            implicitIntent("https://github.com/jimbonlemu")
        }

        findViewById<MaterialButton>(R.id.btn_about_instagram).setOnClickListener {
            try {
                implicitIntentWithPackageInstagram("https://www.instagram.com/_u/jefe_gyu")
            } catch (e: ActivityNotFoundException) {
                implicitIntent("http://instagram.com/jefe_gyu")
            }
        }
    }

    private fun implicitIntent(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun implicitIntentWithPackageInstagram(urlToInstagram: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(urlToInstagram)
            ).setPackage("com.instagram.android")
        )
    }

}



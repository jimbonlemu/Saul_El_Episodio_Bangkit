package com.jimbonlemu.saul_el_episodio.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.jimbonlemu.saul_el_episodio.databinding.ActivityAboutBinding
import com.jimbonlemu.saul_el_episodio.utils.GITHUB_URL
import com.jimbonlemu.saul_el_episodio.utils.INSTAGRAM_INSTALLED_URL
import com.jimbonlemu.saul_el_episodio.utils.INSTAGRAM_PACKAGE
import com.jimbonlemu.saul_el_episodio.utils.INSTAGRAM_URL

class AboutActivity : BackActivity("About Developer") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityAboutBinding.inflate(layoutInflater).apply {
            setContentView(root)
            btnAboutGithub.setOnClickListener { implicitIntent(GITHUB_URL) }
            btnAboutInstagram.setOnClickListener {
                try {
                    implicitIntentWithPackageInstagram(INSTAGRAM_INSTALLED_URL)
                } catch (e: ActivityNotFoundException) {
                    implicitIntent(INSTAGRAM_URL)
                }
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
            ).setPackage(INSTAGRAM_PACKAGE)
        )
    }
}



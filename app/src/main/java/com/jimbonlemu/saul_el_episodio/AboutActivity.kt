package com.jimbonlemu.saul_el_episodio

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class AboutActivity : BackActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.title = "About Developer"

        findViewById<MaterialButton>(R.id.btn_about_github).setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/jimbonlemu")))
        }
        findViewById<MaterialButton>(R.id.btn_about_instagram).setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/_u/jefe_gyu")).setPackage("com.instagram.android"))
            }catch (e: ActivityNotFoundException) {

                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/jefe_gyu")))
            }
        }
    }
}

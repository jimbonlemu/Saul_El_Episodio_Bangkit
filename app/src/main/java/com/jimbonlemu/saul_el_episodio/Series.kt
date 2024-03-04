package com.jimbonlemu.saul_el_episodio

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Series(
    val image:String,
    val title: String,
    val rating: String,
    val season: String,
    val episode: String,
    val synopsis: String,
):Parcelable

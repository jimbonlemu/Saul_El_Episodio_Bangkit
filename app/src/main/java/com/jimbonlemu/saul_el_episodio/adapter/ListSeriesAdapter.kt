package com.jimbonlemu.saul_el_episodio.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jimbonlemu.saul_el_episodio.R
import com.jimbonlemu.saul_el_episodio.data.Series

class ListSeriesAdapter(private val listSeries: ArrayList<Series>) :
    RecyclerView.Adapter<ListSeriesAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivSeriesPic: ImageView = itemView.findViewById(R.id.iv_content_img)
        val tvTitleSeries: TextView = itemView.findViewById(R.id.tv_title_episode)
        val tvSynopsisSeries: TextView = itemView.findViewById(R.id.tv_synopsis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.episode_item_widget, parent, false)
        )
    }


    override fun getItemCount(): Int = listSeries.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val (img, title, rating, season, episode, synopsis) = listSeries[position]

        Glide.with(holder.itemView.context).load(img).into(holder.ivSeriesPic)

        holder.tvTitleSeries.text = "\"$title\" - Season $season, Episode $episode"

        holder.tvSynopsisSeries.text = synopsis

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listSeries[holder.adapterPosition])
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Series)
    }
}

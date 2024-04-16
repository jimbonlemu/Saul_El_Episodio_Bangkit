package com.jimbonlemu.saul_el_episodio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jimbonlemu.saul_el_episodio.data.Series
import com.jimbonlemu.saul_el_episodio.databinding.EpisodeItemWidgetBinding

class ListSeriesAdapter(private val listSeries: List<Series>) :
    RecyclerView.Adapter<ListSeriesAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(private val binding: EpisodeItemWidgetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(series: Series) {
            with(binding) {
                Glide.with(root).load(series.image).into(ivContentImg)
                "\"${series.title}\" - Season ${series.season}, Episode ${series.episode}".also { tvTitleEpisode.text = it }
                tvSynopsis.text = series.synopsis
                root.setOnClickListener { onItemClickCallback.onItemClicked(series) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            EpisodeItemWidgetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listSeries.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(listSeries[position])

    interface OnItemClickCallback {
        fun onItemClicked(data: Series)
    }
}

package com.example.uas_naura

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WahanaAdapter(private val wahanaList: List<Wahana>) : RecyclerView.Adapter<WahanaAdapter.WahanaViewHolder>() {

    class WahanaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wahanaImage: ImageView = itemView.findViewById(R.id.wahanaimage)
        val name: TextView = itemView.findViewById(R.id.namawahana)
        val description: TextView = itemView.findViewById(R.id.deskripsi)
        val category: TextView = itemView.findViewById(R.id.kategori)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WahanaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.wahana_item, parent, false)
        return WahanaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WahanaViewHolder, position: Int) {
        val currentItem = wahanaList[position]
        holder.wahanaImage.setImageResource(currentItem.imageResId)
        holder.name.text = currentItem.name
        holder.description.text = currentItem.description
        holder.category.text = currentItem.category
    }

    override fun getItemCount() = wahanaList.size
}

package com.example.busapp.detaille_itineraire

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.busapp.R

class AdapterArret(private val arrets: List<String>) : RecyclerView.Adapter<AdapterArret.ArretViewHolder>() {

    class ArretViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvArret: TextView = view.findViewById(R.id.textArret)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArretViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_arret, parent, false)
        return ArretViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArretViewHolder, position: Int) {
        holder.tvArret.text = arrets[position].trim()
    }

    override fun getItemCount() = arrets.size
}
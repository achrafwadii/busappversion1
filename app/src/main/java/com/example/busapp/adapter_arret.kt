package com.example.busapp.detaille_itineraire

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.busapp.R

// AdapterArret.kt
class AdapterArret(private val arrets: List<String>) : RecyclerView.Adapter<AdapterArret.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvArret: TextView = view.findViewById(R.id.textArret)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_arret, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvArret.text = "• ${arrets[position]}"
    }

    override fun getItemCount() = arrets.size
}
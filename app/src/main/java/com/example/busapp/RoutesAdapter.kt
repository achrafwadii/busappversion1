package com.example.busapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// RoutesAdapter.kt
class RoutesAdapter(
    private val context: Context,  // Ajout du paramètre Context
    private val routes: List<Route>,  // Liste des routes
    private val onItemClick: (Route) -> Unit  // Callback pour les clics
) : RecyclerView.Adapter<RoutesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textDep: TextView = view.findViewById(R.id.textDep)
        val textDes: TextView = view.findViewById(R.id.textDes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_route, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val route = routes[position]
        holder.textDep.text = "Départ: ${route.dep}"
        holder.textDes.text = "Destination: ${route.des}"

        holder.itemView.setOnClickListener {
            onItemClick(route)
        }
    }

    override fun getItemCount() = routes.size
}
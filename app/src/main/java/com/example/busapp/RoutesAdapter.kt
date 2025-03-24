package com.example.busapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoutesAdapter(
    private val routes: List<Route>,
    private val onItemClick: (Route) -> Unit
) : RecyclerView.Adapter<RoutesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textDep: TextView = view.findViewById(R.id.textDep)
        val textDes: TextView = view.findViewById(R.id.textDes)
        val textHeureDep: TextView = view.findViewById(R.id.textHeureDep)
        val textHeureDes: TextView = view.findViewById(R.id.textHeureDes)
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
        holder.textHeureDep.text = "Heure de départ: ${route.heuredep}"
        holder.textHeureDes.text = "Heure d'arrivée: ${route.heuredes}"

        // Gérer le clic sur un élément
        holder.itemView.setOnClickListener {
            onItemClick(route)
        }
    }

    override fun getItemCount() = routes.size
}

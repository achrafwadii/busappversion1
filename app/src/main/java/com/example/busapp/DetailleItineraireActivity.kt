package com.example.busapp.detaille_itineraire

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.busapp.R
import com.example.busapp.Route
import com.example.busapp.itemitineraire.Itineraire

// DetailleItineraireActivity.kt
class DetailleItineraireActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detaillecard)

        val route = intent.getParcelableExtra<Route>("ROUTE")

        val tvDep = findViewById<TextView>(R.id.tvSource)
        val tvDes = findViewById<TextView>(R.id.tvDestination)
        val tvHeureDep = findViewById<TextView>(R.id.tvHoraireDepart)
        val tvHeureDes = findViewById<TextView>(R.id.tvHoraireArrivee)
        val tvNumBus = findViewById<TextView>(R.id.tvNumBus)
        val recyclerArrets = findViewById<RecyclerView>(R.id.recyclerArrets)

        route?.let {
            tvDep.text = "Départ: ${it.dep}"
            tvDes.text = "Destination: ${it.des}"
            tvHeureDep.text = "Heure départ: ${it.heuredep}"
            tvHeureDes.text = "Heure arrivée: ${it.heuredes}"
            tvNumBus.text = "Bus: ${it.numbus}"

            // Traitement des points d'arrêt
            val arrets = it.pointarret.split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }

            recyclerArrets.layoutManager = LinearLayoutManager(this)
            recyclerArrets.adapter = AdapterArret(arrets)
        }
    }
}
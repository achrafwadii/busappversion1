package com.example.busapp.detaille_itineraire

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.busapp.R
import com.example.busapp.itemitineraire.Itineraire

class DetailleItineraireActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detaillecard)

        // Récupérer l'itinéraire depuis l'intent
        val itineraire = intent.getParcelableExtra<Itineraire>("ITINERAIRE")

        // Initialiser les vues
        val tvSource = findViewById<TextView>(R.id.tvSource)
        val tvDestination = findViewById<TextView>(R.id.tvDestination)
        val tvHoraireDepart = findViewById<TextView>(R.id.tvHoraireDepart)
        val tvHoraireArrivee = findViewById<TextView>(R.id.tvHoraireArrivee)
        val tvNumBus = findViewById<TextView>(R.id.tvNumBus)
        val recyclerArrets = findViewById<RecyclerView>(R.id.recyclerArrets)

        // Remplir les données
        itineraire?.let {
            tvSource.text = it.source
            tvDestination.text = it.destination
            tvHoraireDepart.text = "Départ : ${it.horaireDepart}"
            tvHoraireArrivee.text = "Arrivée : ${it.horaireArrivee}"
            tvNumBus.text = "NumBus : ${it.numbus}"

            // Configurer la liste des arrêts
            val arrets = it.arrets.split(",")
            recyclerArrets.layoutManager = LinearLayoutManager(this)
            recyclerArrets.adapter = AdapterArret(arrets)
        }
    }
}
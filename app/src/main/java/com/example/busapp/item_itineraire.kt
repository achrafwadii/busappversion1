package com.example.busapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.busapp.detaille_itineraire.DetailleItineraireActivity
import com.example.busapp.itemitineraire.Itineraire
import com.example.busapp.itemitineraire.ItineraireAdapter
import com.google.firebase.firestore.FirebaseFirestore

class item_itineraire : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItineraireAdapter
    private val itineraireList = mutableListOf<Itineraire>()
    private val db = FirebaseFirestore.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_itinerair)

        // Récupérer source et destination depuis l'intent
        val source = intent.getStringExtra("src") ?: ""
        val destination = intent.getStringExtra("dst") ?: ""

        Log.d("Intent", "Source : $source, Destination : $destination")

        // Initialiser RecyclerView
        recyclerView = findViewById(R.id.recyclerItineraire)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialiser l'adapter avec la liste d'itinéraires et un gestionnaire de clic
        adapter = ItineraireAdapter(itineraireList) { itineraire ->
            val intent = Intent(this, DetailleItineraireActivity::class.java).apply {
                putExtra("ITINERAIRE", itineraire) // Passer l'itinéraire à l'activité de détail
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // Charger les itinéraires depuis Firestore
        loadItineraires(source, destination)
    }

    private fun loadItineraires(source: String, destination: String) {
        db.collection("iteneraires")
            .whereEqualTo("dep", source)
            .whereEqualTo("dest", destination)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Log.d("Firestore", "Aucun document trouvé pour source=$source, destination=$destination")
                    Toast.makeText(this, "Aucune route trouvée", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("Firestore", "Nombre de documents trouvés : ${documents.size()}")
                    itineraireList.clear()
                    for (document in documents) {
                        Log.d("Firestore", "Document : ${document.data}")
                        val itineraire = Itineraire(
                            source = document.getString("dep") ?: "",
                            destination = document.getString("dest") ?: "",
                            horaireDepart = document.getString("heuredep") ?: "",
                            horaireArrivee = document.getString("heuredest") ?: "",
                            arrets = document.getString("id_point_d_arret") ?: "",
                            numbus = document.getString("busnum") ?: ""
                        )
                        itineraireList.add(itineraire)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Erreur de chargement : ${e.message}")
                Toast.makeText(this, "Erreur de chargement: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
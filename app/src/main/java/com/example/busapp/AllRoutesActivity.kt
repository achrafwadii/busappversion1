package com.example.busapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.busapp.detaille_itineraire.DetailleItineraireActivity
import com.example.busapp.detaille_itineraire.RouteDetailsActivity
import com.google.firebase.firestore.FirebaseFirestore

class AllRoutesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RoutesAdapter
    private val routesList = mutableListOf<Route>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_routes)

        // Initialiser RecyclerView
        recyclerView = findViewById(R.id.recyclerRoutes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Configurer l'adapter
        adapter = RoutesAdapter(
            this,       // Context en premier
            routesList, // Liste des routes en second
            { route ->  // Lambda pour le click
                val intent = Intent(this, DetailleItineraireActivity::class.java).apply {
                    putExtra("ROUTE", route)
                }
                startActivity(intent)
            }
        )

        recyclerView.adapter = adapter
        loadRoutesFromFirestore()
    }

    private fun loadRoutesFromFirestore() {
        db.collection("itinéraire")
            .get()
            .addOnSuccessListener { documents ->
                routesList.clear()
                for (document in documents) {
                    val route = Route(
                        id = document.id,
                        numbus = document.getString("Numbus") ?: "",
                        dep = document.getString("dep") ?: "",
                        des = document.getString("des") ?: "",
                        heuredep = document.getString("heuredep") ?: "",
                        heuredes = document.getString("heuredes") ?: "",
                        pointarret = document.getString("pointarret") ?: ""
                    )
                    routesList.add(route)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erreur de chargement: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun showRouteDetails(route: Route) {
        // Ouvrir une nouvelle activité ou BottomSheet pour afficher les détails
        val intent = Intent(this, DetailleItineraireActivity::class.java).apply {
            putExtra("ROUTE", route)
        }
        startActivity(intent)
    }
}
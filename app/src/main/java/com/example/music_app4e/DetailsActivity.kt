package com.example.music_app4e

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    private lateinit var imgPosterDetalle : ImageView
    private lateinit var tvNombrePeliculaDetalle : TextView
    private lateinit var tvDirectorDetalle : TextView
    private lateinit var tvPeliculaDetalle : TextView
    private lateinit var tvSinopsisDetalle : TextView
    private lateinit var btnFavorito : ImageButton

    private var peliculaFavorita = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgPosterDetalle = findViewById(R.id.imgPosterDetalle)
        tvNombrePeliculaDetalle = findViewById(R.id.tvNombrePeliculaDetalle)
        tvDirectorDetalle = findViewById(R.id.tvDirectorDetalle)
        tvPeliculaDetalle = findViewById(R.id.tvPeliculaDetalle)
        tvSinopsisDetalle = findViewById(R.id.tvSinopsisDetalle)
        btnFavorito = findViewById(R.id.btnFavorito)

        mostrarInformacionPelicula()

    }

    // MÉTODO PARA OBTENER LA INFORMACIÓN QUE VIENE DEL API
    private fun mostrarInformacionPelicula(){

        val idPelicula = intent.getLongExtra("ID_PELICULA", 1)
        val nombrePelicula = intent.getStringExtra("NOMBRE_PELICULA")
        val director = intent.getStringExtra("DIRECTOR_PELICULA")
        val genero = intent.getStringExtra("GENERO_PELICULA")
        val fecha = intent.getStringExtra("FECHA_PELICULA")
        val sinopsis = intent.getStringExtra("SINOPSIS_PELICULA")
        val imagen = intent.getStringExtra("IMAGEN_PELICULA")

        tvNombrePeliculaDetalle.text = nombrePelicula
        tvDirectorDetalle.text = director
        tvPeliculaDetalle.text = genero + " - " + (fecha?.take(10) ?: "Sin fecha")
        tvSinopsisDetalle.text = sinopsis ?: "Sin sinopsis"

        Glide.with(this)
            .load(imagen)
            .centerCrop()
            .into(imgPosterDetalle)

        // BOTÓN DE GUARDADO / FAVORITOS
        // (por ahora solo cambia el ícono; después esta información se guardará en la BD)
        btnFavorito.setOnClickListener {
            peliculaFavorita = !peliculaFavorita

            if (peliculaFavorita){
                btnFavorito.setImageResource(R.drawable.favorito)
                Toast.makeText(this, "Película $nombrePelicula agregada a favoritos", Toast.LENGTH_SHORT).show()
            }else{
                btnFavorito.setImageResource(R.drawable.no_favorito)
                Toast.makeText(this, "Película $nombrePelicula eliminada de favoritos", Toast.LENGTH_SHORT).show()
            }
        }

    }

}

package com.example.appcapook.ui.views

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appcapook.R
import com.squareup.picasso.Picasso

class BookDatailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_datails)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val imageView: ImageView = findViewById(R.id.bookImage)
        val tituloText: TextView = findViewById(R.id.textTitulo)
        val autorText: TextView = findViewById(R.id.textAutor)
        val statusText: TextView = findViewById(R.id.textStatus)
        val sinopseText: TextView = findViewById(R.id.textSinopse)

        // Receber os dados enviados via Intent
        val titulo = intent.getStringExtra("titulo")
        val autor = intent.getStringExtra("autor")
        val status = intent.getStringExtra("status")
        val sinopse = intent.getStringExtra("sinopse")
        val imageUrl = intent.getStringExtra("imageUrl")

        // Exibir os dados
        tituloText.text = titulo
        autorText.text = autor
        statusText.text = status
        sinopseText.text = sinopse

        // Carregar a imagem do livro
        if (!imageUrl.isNullOrEmpty()) {
            Picasso.get().load(imageUrl).into(imageView)
        } else {
            imageView.setImageResource(R.drawable.baseline_image_24)
        }
    }
}
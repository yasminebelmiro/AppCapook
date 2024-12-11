package com.example.appcapook.ui.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcapook.Book
import com.example.appcapook.R
import com.example.appcapook.ui.adapters.BookReadingAdapter
import com.example.appcapook.ui.adapters.MetasAdapter
import com.example.appcapook.data.BookshelfService
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         val db = FirebaseFirestore.getInstance()
         val bookshelfService = BookshelfService(db)

        val books = Book.getBooks()
        val recycleViewReading = findViewById<RecyclerView>(R.id.recycleReading)
        val recyclerViewMetas = findViewById<RecyclerView>(R.id.recycleMetas)

        recycleViewReading.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycleViewReading.adapter = BookReadingAdapter(books)

        recyclerViewMetas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewMetas.adapter = MetasAdapter(books)




    }
}

package com.example.appcapook.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcapook.Book
import com.example.appcapook.R
import com.example.appcapook.ui.adapters.BookReadingAdapter
import com.example.appcapook.ui.adapters.MetasAdapter
import com.example.appcapook.data.BookshelfService
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var bookshelfService: BookshelfService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragmento
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Inicializa o Firestore e o BookshelfService
        val db = FirebaseFirestore.getInstance()
        bookshelfService = BookshelfService(db)

        // Configura os RecyclerViews
        setupRecyclerViews(view)

        return view
    }

    private fun setupRecyclerViews(view: View) {
        val books = Book.getBooks()
        val recycleViewReading = view.findViewById<RecyclerView>(R.id.recycleReading)
        val recyclerViewMetas = view.findViewById<RecyclerView>(R.id.recycleMetas)

        recycleViewReading.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recycleViewReading.adapter = BookReadingAdapter(books)

        recyclerViewMetas.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewMetas.adapter = MetasAdapter(books)
    }
}
package com.example.appcapook.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcapook.Book
import com.example.appcapook.BookshevesAdapter
import com.example.appcapook.R

class BookshelvesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragmento
        val view = inflater.inflate(R.layout.fragment_bookshelves, container, false)

        // Obter a lista de livros
        val books =Book.getBooks()

        // Configurar os RecyclerViews
        setupRecyclerViews(view, books)
        val addEstanteQLerButton: Button = view.findViewById(R.id.addQueroLer)
        addEstanteQLerButton.setOnClickListener {
            onClickAddBook()
        }

        val addEstanteLendoButton: Button = view.findViewById(R.id.addLendo)
        addEstanteLendoButton.setOnClickListener {
            onClickAddBook()
        }

        val addEstanteLidosButton: Button = view.findViewById(R.id.addLidos)
        addEstanteLidosButton.setOnClickListener {
            onClickAddBook()
        }

        val addEstanteFavButton: Button = view.findViewById(R.id.addFavoritos)
        addEstanteFavButton.setOnClickListener {
            onClickAddBook()
        }

        val addEstanteAbandonadosButton: Button = view.findViewById(R.id.addAbandonados)
        addEstanteAbandonadosButton.setOnClickListener {
            onClickAddBook()
        }

        return view
    }

    private fun setupRecyclerViews(view: View, books: List<Book>) {
        val recyclerViewQueroLer: RecyclerView = view.findViewById(R.id.recycleQueroLer)
        recyclerViewQueroLer.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewQueroLer.adapter = BookshevesAdapter(books)

        val recyclerViewLendo: RecyclerView = view.findViewById(R.id.recycleLendo)
        recyclerViewLendo.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewLendo.adapter = BookshevesAdapter(books)

        val recyclerViewAbandonados: RecyclerView = view.findViewById(R.id.recycleAbandonados)
        recyclerViewAbandonados.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewAbandonados.adapter = BookshevesAdapter(books)

        val recyclerViewLidos: RecyclerView = view.findViewById(R.id.recycleLidos)
        recyclerViewLidos.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewLidos.adapter = BookshevesAdapter(books)

        val recyclerViewFavoritos: RecyclerView = view.findViewById(R.id.recycleFavoritos)
        recyclerViewFavoritos.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewFavoritos.adapter = BookshevesAdapter(books)
    }

     fun onClickAddBook() {
        // Navegar para o SearchFragment
        findNavController().navigate(R.id.menu_search)
    }
}
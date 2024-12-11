package com.example.appcapook.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcapook.R
import com.example.appcapook.ui.adapters.BookAdapter
import com.example.appcapook.data.BookHttp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragmento
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Referência para o EditText de busca
        val inputBuscarLivro: EditText = view.findViewById(R.id.InputBuscarLivro)

        // Referência para o RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycleViewBooks)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        // Configuração para buscar livros quando o usuário pressionar Enter (ação de pesquisa)
        inputBuscarLivro.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = inputBuscarLivro.text.toString()

                if (query.isNotEmpty()) {
                    // Iniciar a busca pelos livros
                    buscarLivros(query, recyclerView)
                } else {
                    Toast.makeText(requireContext(), "Por favor, insira um título, autor ou editora", Toast.LENGTH_SHORT).show()
                }
                true  // Marca a ação como tratada
            } else {
                false  // Para outros tipos de ações, não fazemos nada
            }
        }

        return view
    }

    private fun buscarLivros(query: String, recyclerView: RecyclerView) {
        CoroutineScope(Dispatchers.IO).launch {
            val books = BookHttp.searchBook(query)?.items ?: emptyList()
            withContext(Dispatchers.Main) {
                if (books.isNotEmpty()) {
                    // Atualizar o RecyclerView com os livros encontrados
                    val adapter = BookAdapter(books)
                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(requireContext(), "Nenhum livro encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
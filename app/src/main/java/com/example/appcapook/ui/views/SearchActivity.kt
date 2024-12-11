package com.example.appcapook.ui.views

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcapook.R
import com.example.appcapook.ui.adapters.BookAdapter
import com.example.appcapook.data.BookHttp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Referência para o EditText de busca
        val inputBuscarLivro: EditText = findViewById(R.id.InputBuscarLivro)

        // Referência para o RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycleViewBooks)
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        // Configuração para buscar livros quando o usuário pressionar Enter (ação de pesquisa)
        inputBuscarLivro.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = inputBuscarLivro.text.toString()

                if (query.isNotEmpty()) {
                    // Iniciar a busca pelos livros
                    buscarLivros(query, recyclerView)
                } else {
                    Toast.makeText(this, "Por favor, insira um título, autor ou editora", Toast.LENGTH_SHORT).show()
                }
                true  // Marca a ação como tratada
            } else {
                false  // Para outros tipos de ações, não fazemos nada
            }
        }
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
                    Toast.makeText(this@SearchActivity, "Nenhum livro encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


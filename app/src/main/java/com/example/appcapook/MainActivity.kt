package com.example.appcapook

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcapook.model.BookHttp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                    Toast.makeText(this@MainActivity, "Nenhum livro encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

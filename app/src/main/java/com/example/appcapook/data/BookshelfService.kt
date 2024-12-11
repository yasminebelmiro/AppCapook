package com.example.appcapook.data

import com.example.appcapook.model.Book
import com.example.appcapook.model.Bookshelf
import com.google.firebase.firestore.FirebaseFirestore

class BookshelfService(private val db: FirebaseFirestore) {

    // Cria uma estante
    fun addBookshelf(bookshelf: Bookshelf, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("bookshelves")
            .add(bookshelf)
            .addOnSuccessListener { documentReference ->
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    // Retorna todas as estantes
    fun getAllBookshelves(onSuccess: (List<Bookshelf>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("bookshelves")
            .get()
            .addOnSuccessListener { result ->
                val bookshelves = result.documents.mapNotNull { doc ->
                    doc.toObject(Bookshelf::class.java)
                }
                onSuccess(bookshelves)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    // Retorna uma estante pelo id
    fun getBookshelfById(id: String, onSuccess: (Bookshelf?) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("bookshelves").document(id)
            .get()
            .addOnSuccessListener { document ->
                val bookshelf = document.toObject(Bookshelf::class.java)
                onSuccess(bookshelf)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    // Atualiza uma estnte pelo id
    fun updateBookshelf(id: String, updatedBookshelf: Bookshelf, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("bookshelves").document(id)
            .set(updatedBookshelf)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    // Exclui uma estante
    fun deleteBookshelf(id: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("bookshelves").document(id)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    // Adiciona um livro a uma estante
    fun addBookToBookshelf(bookshelfId: String, book: Book, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        // Obter a prateleira existente
        getBookshelfById(bookshelfId, { bookshelf ->
            bookshelf?.let {
                // Adicionar o livro à lista de livros na estante
                it.books.add(book)

                // Atualizar a prateleira com a nova lista de livros
                updateBookshelf(bookshelfId, it, onSuccess, onFailure)
            } ?: onFailure(Exception("Bookshelf not found"))
        }, { e ->
            onFailure(e)
        })
    }

    // Remove um livro de uma estante
    fun removeBookFromBookshelf(bookshelfId: String, bookId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        // Obter a prateleira existente
        getBookshelfById(bookshelfId, { bookshelf ->
            bookshelf?.let {
                // Filtrar o livro que deve ser removido
                it.books = it.books.filter { book -> book.volume.id != bookId }.toMutableList()

                // Atualizar a prateleira com a nova lista de livros
                updateBookshelf(bookshelfId, it, onSuccess, onFailure)
            } ?: onFailure(Exception("Bookshelf not found"))
        }, { e ->
            onFailure(e)
        })
    }
}
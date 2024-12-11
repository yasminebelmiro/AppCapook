
package com.example.appcapook

data class Book(
    val img: Int,
    val title: String,
    val author: String,
    val pageCount: Int,
    val readingProgress: Int
) {
    companion object {

        fun getBooks(): List<Book> {
            return listOf(
                Book(R.drawable.imagem1, "Título 1", "Autor 1", 300, 50),
                Book(R.drawable.imagem2, "Título 2", "Autor 2", 250, 20),
                Book(R.drawable.imagem3, "Título 1", "Autor 1", 300, 50),
                Book(R.drawable.imagem4, "Título 2", "Autor 2", 250, 20),
                )
        }


    }
}

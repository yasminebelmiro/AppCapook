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
                Book(R.drawable.baseline_image_24, "Título 1", "Autor 1", 300, 50),
                Book(R.drawable.baseline_image_24, "Título 2", "Autor 2", 250, 20),

                )
        }
    }
}

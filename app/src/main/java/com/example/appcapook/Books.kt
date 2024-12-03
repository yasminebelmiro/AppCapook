package com.example.appcapook

data class Book(val img: Int) {
    companion object {
        fun getBooks(): List<Book> {
            return listOf(
                Book(R.drawable.imagem1),
                Book(R.drawable.imagem2),
                Book(R.drawable.imagem3),
                Book(R.drawable.imagem4),
                Book(R.drawable.imagem5),
                Book(R.drawable.imagem6),
                Book(R.drawable.imagem7),
                Book(R.drawable.imagem8),
                Book(R.drawable.imagem9)
            )
        }
    }
}

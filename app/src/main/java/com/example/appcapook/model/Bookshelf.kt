package com.example.appcapook.model

data class Bookshelf (
    val id: String,
    val name: String,
    var books: MutableList<Book> = mutableListOf()
)
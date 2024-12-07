package com.example.appcapook.model

data class SearchResult(
    val items:List<Volume>,
    val kind: String?,
    val totalItems: Int?
)

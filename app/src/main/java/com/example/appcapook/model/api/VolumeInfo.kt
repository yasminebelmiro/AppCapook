package com.example.appcapook.model.api

data class VolumeInfo(
    val title:String?,
    val subtitle: String?,
    val description: String?,
    val authors: List<String>?,
    val publisher: String?,
    val publishedDate: String?,
    val pageCount: Int?,
    val categories : List<String>?,
    val maturityRating: String?,
    val imageLinks: ImageLinks?,
    val language: String?
)

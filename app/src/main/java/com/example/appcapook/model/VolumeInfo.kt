package com.example.appcapook.model

data class VolumeInfo(
    val title:String?,
    val description: String?,
    val authors: List<String>?,
    val publisher: String?,
    val dataPublisher: String?,
    val pageCount: Int?,
    val imageLinks: ImageLinks?
)

package com.example.appcapook.model

import com.example.appcapook.model.api.Volume
import com.google.firebase.Timestamp

data class Book (
    val volume: Volume,
    val lastPage: Int?,
    val readingProgress: Int?,
    val notes: List<String>?,
    val addedDate: Timestamp?,
    val readTime: Timestamp?,
    val status: Status
)
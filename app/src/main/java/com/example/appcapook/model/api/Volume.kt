package com.example.appcapook.model.api

data class Volume(
    val kind: String?,
    val id:String?,
    val etag: String?,
    val selfLink:String,
    val volumeInfo: VolumeInfo
)

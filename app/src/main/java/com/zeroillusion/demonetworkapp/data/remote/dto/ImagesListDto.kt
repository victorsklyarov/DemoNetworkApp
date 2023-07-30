package com.zeroillusion.demonetworkapp.data.remote.dto

data class ImagesListDto(
    val hits: List<ImageDto>,
    val total: Int,
    val totalHits: Int
)
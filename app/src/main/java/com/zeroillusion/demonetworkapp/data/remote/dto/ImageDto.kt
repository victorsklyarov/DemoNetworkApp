package com.zeroillusion.demonetworkapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.zeroillusion.demonetworkapp.data.local.entity.ImageEntity

data class ImageDto(
    val collections: Int,
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    @SerializedName("user_id")
    val userId: Int,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int
) {

    fun toImageEntity(): ImageEntity {
        return ImageEntity(
            id = id,
            webformatURL = webformatURL,
            tags = tags
        )
    }
}
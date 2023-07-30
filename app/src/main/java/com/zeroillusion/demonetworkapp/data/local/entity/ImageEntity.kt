package com.zeroillusion.demonetworkapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zeroillusion.demonetworkapp.domain.model.Image

@Entity
data class ImageEntity(
    @PrimaryKey val id: Int,
    val webformatURL: String,
    val tags: String
) {

    fun toImage(): Image {
        return Image(
            url = webformatURL,
            tags = tags
        )
    }
}
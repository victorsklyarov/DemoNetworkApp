package com.zeroillusion.demonetworkapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zeroillusion.demonetworkapp.data.local.entity.ImageEntity

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImages(images: List<ImageEntity>)

    @Query("SELECT * FROM imageentity")
    suspend fun getAllImages(): List<ImageEntity>
}
package com.zeroillusion.demonetworkapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zeroillusion.demonetworkapp.data.local.entity.ImageEntity

@Database(
    entities = [ImageEntity::class],
    version = 1
)
abstract class ImagesDatabase : RoomDatabase() {

    abstract val dao: ImagesDao
}
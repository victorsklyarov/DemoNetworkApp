package com.zeroillusion.demonetworkapp.domain.repository

import com.zeroillusion.demonetworkapp.domain.model.Image
import com.zeroillusion.demonetworkapp.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NetworkAppRepository {

    suspend fun getImagesList(): Flow<Resource<List<Image>>>
}
package com.zeroillusion.demonetworkapp.domain.use_case

import com.zeroillusion.demonetworkapp.domain.model.Image
import com.zeroillusion.demonetworkapp.domain.repository.NetworkAppRepository
import com.zeroillusion.demonetworkapp.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetImages(
    private val repository: NetworkAppRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Image>>> {
        return repository.getImagesList()
    }
}

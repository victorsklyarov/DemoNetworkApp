package com.zeroillusion.demonetworkapp.data.repository

import com.zeroillusion.demonetworkapp.data.local.ImagesDao
import com.zeroillusion.demonetworkapp.data.remote.PixabayApi
import com.zeroillusion.demonetworkapp.domain.model.Image
import com.zeroillusion.demonetworkapp.domain.repository.NetworkAppRepository
import com.zeroillusion.demonetworkapp.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NetworkAppRepositoryImpl(
    private val api: PixabayApi,
    private val dao: ImagesDao
) : NetworkAppRepository {

    override suspend fun getImagesList(): Flow<Resource<List<Image>>> = flow {
        emit(Resource.Loading())

        val imagesList = dao.getAllImages().map { it.toImage() }
        emit(Resource.Loading(data = imagesList))

        try {
            val remoteImagesList = api.getAllImages()
            dao.insertImages(remoteImagesList.hits.map { it.toImageEntity() })
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "HttpException",
                data = imagesList
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "No internet connection",
                data = imagesList
            ))
        } catch (e: Exception) {
            emit(Resource.Error(
                message = e.message ?: "Unknown error",
                data = imagesList
            ))
        }

        val newImagesList = dao.getAllImages().map { it.toImage() }
        emit(Resource.Success(newImagesList))
    }
}
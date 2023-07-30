package com.zeroillusion.demonetworkapp.data.remote

import com.zeroillusion.demonetworkapp.BuildConfig
import com.zeroillusion.demonetworkapp.data.remote.dto.ImagesListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    //@GET("/api/")
    //suspend fun searchForImage(
    //    @Query("q") searchQuery: String,
    //    @Query("key") apiKey: String = BuildConfig.API_KEY
    //) : Response<ImageResponse>

    @GET("/api/")
    suspend fun getAllImages(
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ) : ImagesListDto
}
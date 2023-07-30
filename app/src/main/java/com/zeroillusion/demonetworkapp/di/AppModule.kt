package com.zeroillusion.demonetworkapp.di

import android.app.Application
import androidx.room.Room
import coil.ImageLoader
import coil.request.CachePolicy
import com.zeroillusion.demonetworkapp.data.local.ImagesDatabase
import com.zeroillusion.demonetworkapp.data.remote.PixabayApi
import com.zeroillusion.demonetworkapp.data.repository.NetworkAppRepositoryImpl
import com.zeroillusion.demonetworkapp.domain.repository.NetworkAppRepository
import com.zeroillusion.demonetworkapp.domain.use_case.GetImages
import com.zeroillusion.demonetworkapp.domain.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetImages(repository: NetworkAppRepository): GetImages {
        return GetImages(repository)
    }

    @Provides
    @Singleton
    fun provideNetworkAppRepository(
        db: ImagesDatabase,
        api: PixabayApi
    ): NetworkAppRepository {
        return NetworkAppRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideImagesDatabase(app: Application): ImagesDatabase {
        return Room.databaseBuilder(
            app,
            ImagesDatabase::class.java,
            "images_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePixabayApi(): PixabayApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PixabayApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImageLoader(
        app: Application
    ): ImageLoader {
        return ImageLoader.Builder(app)
            .crossfade(true)
            .respectCacheHeaders(false)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()
    }
}
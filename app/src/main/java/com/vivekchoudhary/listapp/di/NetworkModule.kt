package com.vivekchoudhary.listapp.di

import com.vivekchoudhary.listapp.common.DefaultDispatcherProvider
import com.vivekchoudhary.listapp.common.DispatcherProvider
import com.vivekchoudhary.listapp.repository.PostService
import com.vivekchoudhary.listapp.repository.PostsRepository
import com.vivekchoudhary.listapp.repository.PostsRepositoryImpl
import com.vivekchoudhary.listapp.utils.Constants.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dependency Injection using Hilt
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindsDispatcherProvider(dispatcherProvider: DefaultDispatcherProvider): DispatcherProvider

    @Binds
    abstract fun providePostsRepository(postsRepository: PostsRepositoryImpl): PostsRepository

    companion object {
        @Singleton
        @Provides
        fun providesRetrofit(): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        fun providesPostServiceApi(retrofit: Retrofit): PostService {
            return retrofit.create(PostService::class.java)
        }
    }
}
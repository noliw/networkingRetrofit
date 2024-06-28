package com.example.networkingretrofit.networking

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {
    private fun getLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
    }

    @Provides
    @Singleton
    @Named("okhttp-jsonplaceholder")
    fun provideOkHttp(addTokenInterceptor: AddTokenInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(addTokenInterceptor)
            .addInterceptor(getLoggingInterceptor())
            .build()
    }

    @Provides
    @Singleton
    @Named("retrofit-jsonplaceholder")
    fun provideRetrofit(@Named("okhttp-jsonplaceholder") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(@Named("retrofit-jsonplaceholder") retrofit: Retrofit): JsonPlaceholderAPI =
        retrofit.create(JsonPlaceholderAPI::class.java)
}
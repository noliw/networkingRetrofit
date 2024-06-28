package com.example.networkingretrofit.week6.image_upload

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

// Define a Hilt module named NetworkingModule
@Module
// Specify that this module will be installed in the SingletonComponent
@InstallIn(SingletonComponent::class)
class NetworkingModule {

    // Define a private function to get a logging interceptor for HTTP requests
    private fun getLoggingInterceptor(): Interceptor {
        // Create a logging interceptor that logs the body of HTTP requests
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(
            // Set the logging level to BODY to log the full request and response bodies
            HttpLoggingInterceptor.Level.BODY
        )
    }

    // Provide a singleton instance of OkHttpClient
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        // Create an OkHttpClient builder
        return OkHttpClient.Builder()
            // Add the logging interceptor to the OkHttpClient
            .addInterceptor(getLoggingInterceptor())
            // Build and return the OkHttpClient instance
            .build()
    }

    // Provide a singleton instance of Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        // Create a Retrofit builder
        return Retrofit.Builder()
            // Set the OkHttpClient for Retrofit to use for network requests
            .client(okHttpClient)
            // Set the base URL for the API
            .baseUrl("https://freeimage.host/api/1/")
            // Add a converter factory for serializing and deserializing JSON
            .addConverterFactory(MoshiConverterFactory.create())
            // Build and return the Retrofit instance
            .build()
    }

    // Provide a singleton instance of ImageApi
    @Provides
    @Singleton
    fun provideImageApi(retrofit: Retrofit): ImageApi {
        // Create and return an implementation of the ImageApi interface using Retrofit
        return retrofit.create(ImageApi::class.java)
    }
}

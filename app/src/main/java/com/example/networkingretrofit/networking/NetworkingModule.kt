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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {

    fun getLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
    }
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(getLoggingInterceptor())
            .build()

    }

    // Provides annotation tells Hilt how to create an instance of a dependency.
    // @Singleton ensures a single instance of Retrofit is created and shared.
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        // Retrofit.Builder is used to create a new instance of Retrofit.
        return Retrofit.Builder()
            // baseUrl sets the base URL for the HTTP requests.
            // All requests will be relative to this URL.
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            // addConverterFactory adds a converter factory for serialization and deserialization of objects. GsonConverterFactory is used to convert JSON to Kotlin objects and vice versa.
            .addConverterFactory(MoshiConverterFactory.create())
            // build finalizes the creation of the Retrofit instance
            .build()
    }


    // Provides the API service instance using the Retrofit instance.
    // @Singleton ensures a single instance of the API service is created and shared.
    @Provides
    @Singleton
    // Creates an implementation of the API endpoints defined by the JsonPlaceholderApiService interface.
    fun provideApiService(retrofit: Retrofit): Retrofit = retrofit.create(Retrofit::class.java)
}

package com.example.networkingretrofit.pagination

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

// Annotation to define a Dagger module
@Module
// Specifies that the module is installed in the SingletonComponent
@InstallIn(SingletonComponent::class)
class NetworkModule {

    // Private function to provide an HTTP logging interceptor for debugging purposes
    private fun getLoggingInterceptor(): Interceptor {
        // Creates an instance of HttpLoggingInterceptor with the default logger
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(
            // Sets the logging level to BODY to log request and response bodies
            HttpLoggingInterceptor.Level.BODY
        )
    }

    // Provides an OkHttpClient instance with logging interceptor
    @Provides // Annotation to mark the function as a provider of a dependency
    @Singleton // Ensures a single instance is created and shared
    @Named("okhttp-github") // Qualifies the provided instance with a name for disambiguation
    fun provideOkHttp(): OkHttpClient {
        // Builds and returns an OkHttpClient instance with the logging interceptor
        return OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor()) // Adds the logging interceptor to the client
            .build() // Builds the OkHttpClient instance
    }

    // Provides a Retrofit instance configured with the OkHttpClient
    @Provides // Annotation to mark the function as a provider of a dependency
    @Singleton // Ensures a single instance is created and shared
    @Named("retrofit-github") // Qualifies the provided instance with a name for disambiguation
    fun provideRetrofit(@Named("okhttp-github") okHttpClient: OkHttpClient): Retrofit {
        // Builds and returns a Retrofit instance configured with the OkHttpClient and base URL
        return Retrofit.Builder()
            .client(okHttpClient) // Sets the OkHttpClient for the Retrofit instance
            .baseUrl("https://api.github.com/") // Sets the base URL for the API requests
            .addConverterFactory(MoshiConverterFactory.create()) // Adds a converter factory for JSON parsing using Moshi
            .build() // Builds the Retrofit instance
    }

    // Provides an implementation of the GitHubApi interface using the Retrofit instance
    @Provides // Annotation to mark the function as a provider of a dependency
    @Singleton // Ensures a single instance is created and shared
    fun provideGitHubApi(@Named("retrofit-github") retrofit: Retrofit): GitHubApi =
        retrofit.create(GitHubApi::class.java) // Creates an implementation of the GitHubApi interface using Retrofit
}

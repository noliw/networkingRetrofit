package com.example.networkingretrofit.networking

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

// Define a custom interceptor class that implements the Interceptor interface
class AddTokenInterceptor @Inject constructor() : Interceptor {

    // Override the intercept method to modify outgoing requests
    override fun intercept(chain: Interceptor.Chain): Response {
        // Create a new request builder from the original request
        val newRequestBuilder = chain.request().newBuilder()

        // Run a blocking coroutine to perform token retrieval and header addition
        runBlocking {
            // Retrieve token from datastore and pass it to new request
            newRequestBuilder.addHeader(
                "Authorization",
                "603b3273-572b-4863-9238-2dc58f54970f"
            )
        }

        // Proceed with the modified request
        return chain.proceed(newRequestBuilder.build())
    }
}
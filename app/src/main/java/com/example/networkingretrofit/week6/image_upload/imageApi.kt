package com.example.networkingretrofit.week6.image_upload

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

// Define an interface named ImageApi
interface ImageApi {
    // Specify that this function makes an HTTP POST request to the "upload" endpoint
    @POST("upload")
    // Define a suspending function named uploadImage that uploads an image
    suspend fun uploadImage(
        // Add a query parameter named "key" with a default value
        @Query("key") key: String = "6d207e02198a847aa98d0a2a901485a5",
        // Add a body parameter named body of type RequestBody
        @Body body: RequestBody
        // The function returns a Response object containing an ImageUploadResponse
    ): Response<ImageUploadResponse>
}

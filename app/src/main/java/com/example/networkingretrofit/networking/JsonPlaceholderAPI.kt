package com.example.networkingretrofit.networking

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JsonPlaceholderAPI {
    @GET(value = "posts")
//    fun getPosts(): Call<List<PostModel>>
    suspend fun getPosts(): List<PostModel>

    @POST(value = "posts")
    suspend fun createPost(
        @Body post: PostModel
    ): PostModel

    @DELETE("posts/{postId}")
    suspend fun deletePost(@Path("postId") postId: Int)
}
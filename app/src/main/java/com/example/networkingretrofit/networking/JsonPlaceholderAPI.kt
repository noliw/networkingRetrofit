package com.example.networkingretrofit.networking
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceholderAPI {
    @GET(value = "posts")
    fun getPosts(): Call<List<PostModel>>

}
package com.example.networkingretrofit.networking
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceholderAPI {
    @GET(value = "https://jsonplaceholder.typicode.com/posts")
    fun getPosts(): Call<List<PostModel>>

}
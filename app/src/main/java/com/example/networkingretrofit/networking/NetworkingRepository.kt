package com.example.networkingretrofit.networking

import com.example.networkingretrofit.debugLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

// Repository class to handle data operations
class NetworkingRepository @Inject constructor(
    // Inject the API service into the repository
    private val api: JsonPlaceholderAPI
) {

//    // Function to fetch posts from the API
//    fun getPosts() {
//        // Make an asynchronous network call to fetch posts
//        val networkCall = api.getPosts()
//        // Enqueue the network call to execute it asynchronously
//        networkCall.enqueue(object: Callback<List<PostModel>> {
//            // Called when the network request is successful
//            override fun onResponse(call: Call<List<PostModel>>, response: Response<List<PostModel>>) {
//                // Get the body of the response, which contains the list of posts
//                val posts = response.body()
//                // Log the titles of the posts
//                debugLog(posts?.map { it.title }?.joinToString(" || ").toString())
//            }
//
//            // Called when the network request fails
//            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
//                // Log the failure message
//                debugLog("Failed to connect")
//            }
//        })
//    }

    suspend fun getPosts(): List<PostModel> {
        val posts = api.getPosts()
        return posts
    }

    suspend fun createPost(post: PostModel): PostModel {
        return api.createPost(post)
    }

    suspend fun deletePost(postId: Int) {
        api.deletePost(postId)
    }

}
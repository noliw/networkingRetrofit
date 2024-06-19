package com.example.networkingretrofit.networking

import com.example.networkingretrofit.debugLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NetworkingRepository @Inject constructor(
    private val api: JsonPlaceholderAPI
) {

     fun getPosts() {
         val networkCall = api.getPosts()
         val response = networkCall.enqueue(object: Callback<List<PostModel>>{
             override fun onResponse(
                 call: Call<List<PostModel>>,
                 response: Response<List<PostModel>>
             ){
                 val posts = response.body()
                     debugLog(posts?.map{it.title}?.joinToString( "||" ).toString())
             }
             override fun onFailure(call: Call<List<PostModel>>, t: Throwable){
                 debugLog("Failed to connect")
             }
         })

    }
}
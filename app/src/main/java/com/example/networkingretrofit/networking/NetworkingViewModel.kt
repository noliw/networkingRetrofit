package com.example.networkingretrofit.networking

import androidx.lifecycle.ViewModel
import com.example.networkingretrofit.debugLog
import javax.inject.Inject

class NetworkingViewModel @Inject constructor(
    private val networkingRepository: NetworkingRepository
): ViewModel() {

    fun getPosts(){
        val posts = networkingRepository.getPosts()
        posts.forEach{ post ->
            debugLog("Post: ${post.title}")
        }
    }
}
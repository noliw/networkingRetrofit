package com.example.networkingretrofit.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkingretrofit.debugLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

// Annotation to make this ViewModel injectable with Hilt
@HiltViewModel
class NetworkingViewModel @Inject constructor(
    // Inject the NetworkingRepository into the ViewModel
    private val networkingRepository: NetworkingRepository
) : ViewModel() {
    // StateFlow to hold the UI state for posts
    private val _posts = MutableStateFlow<PostScreenUIState>(PostScreenUIState.Initial)
    // Publicly exposed immutable StateFlow
    val posts: StateFlow<PostScreenUIState> get() = _posts.asStateFlow()

    // Function to fetch posts
    fun getPosts() {
        // Launch a coroutine in the ViewModel's scope
        viewModelScope.launch {
            // Set the state to loading before starting the fetch
            _posts.value = PostScreenUIState.Loading
            try {
                // Fetch posts from the repository
                val posts = networkingRepository.getPosts()
                // Set the state to success with the fetched posts
                _posts.value = PostScreenUIState.Success(posts)
            } catch (e: IOException) {
                // Set the state to error if an exception occurs
                _posts.value = PostScreenUIState.Error(e.message.toString())
            }
        }
    }

    // Function to create a new post
    fun createPost(post: PostModel) {
        // Launch a coroutine in the ViewModel's scope
        viewModelScope.launch {
            try {
                // Create a new post using the repository
                val post = networkingRepository.createPost(post)
                // Log the created post
                debugLog("post: $post")
            } catch (e: IOException) {
                // Log the error if an exception occurs
                debugLog("Failed to create post: ${e.message}")
            }
        }
    }

    // Function to delete a post
    fun deletePost(postId: Int) {
        // Launch a coroutine in the ViewModel's scope
        viewModelScope.launch {
            try {
                // Delete the post using the repository
                networkingRepository.deletePost(postId)
            } catch (e: IOException) {
                // Log the error if an exception occurs
                debugLog("Failed to delete post: ${e.message}")
            }
        }
    }
}
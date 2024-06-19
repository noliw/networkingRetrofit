package com.example.networkingretrofit.networking

// Define a sealed interface to represent different states of the UI
sealed interface PostScreenUIState {

    // Represents the initial state of the UI before any data is loaded
    data object Initial : PostScreenUIState

    // Represents the loading state of the UI when data is being fetched
    data object Loading : PostScreenUIState

    // Represents the success state of the UI when data is successfully fetched
    data class Success(val posts: List<PostModel>) : PostScreenUIState

    // Represents the error state of the UI when an error occurs during data fetching
    data class Error(val message: String) : PostScreenUIState
}

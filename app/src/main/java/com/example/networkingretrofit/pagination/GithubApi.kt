package com.example.networkingretrofit.pagination

import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    // Defines a GET request to the specified endpoint
    @GET("search/repositories")
    suspend fun searchRepositories(
        // Query parameter to search for Kotlin repositories
        @Query("q") query: String = "language:kotlin",
        // Query parameter to sort results by star count
        @Query("sort") sort: String = "stars",
        // Query parameter to order results in descending order
        @Query("order") order: String = "desc",
        // Query parameter to specify the page number
        @Query("page") page: Int = 1,
        // Query parameter to specify the number of results per page
        @Query("per_page") size: Int = 10
    ): SearchRepositoryResponse // The function returns a SearchRepositoryResponse object
}
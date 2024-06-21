package com.example.networkingretrofit.pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Define a class GitHubRepository that will handle data operations for GitHub repositories
class GitHubRepository @Inject constructor(
    // Injects an instance of GitHubApi to be used by this repository
    private val gitHubApi: GitHubApi
) {
    // Defines a function to search for repositories with a default query parameter
    fun searchRepositories(query: String = "language:kotlin"): Flow<PagingData<Repository>> =
        // Creates a Pager to handle paging configuration and data source
        Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 10,
                initialLoadSize = 30
            )
        ) {
            // Defines the data source for the Pager
            RepositorySource(githubApi = gitHubApi, query = query)
        }.flow // Converts the Pager to a Flow that emits PagingData
}

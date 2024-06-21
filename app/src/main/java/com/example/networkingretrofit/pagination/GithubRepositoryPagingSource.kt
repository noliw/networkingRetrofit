package com.example.networkingretrofit.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.io.IOException

// Constant to define the first page number
private const val FIRST_PAGE = 1

// Define a class RepositorySource that extends PagingSource for paginated data loading
class RepositorySource(
    // Injects an instance of GitHubApi to be used by this source
    private val githubApi: GitHubApi,
    // Sets a default query for searching repositories, defaulting to Kotlin language
    private val query: String = "language:kotlin"
) : PagingSource<Int, Repository>() {

    // Function to get the refresh key for paging state
    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        // Returns null as the refresh key, meaning no specific key for refreshing
        return null
    }

    // Suspended function to load data pages
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            // Determines the page number to load, defaulting to the first page if not specified
            val page = params.key ?: FIRST_PAGE
            // Makes an API call to search for repositories with the specified
            // query, page number, and page size
            val response: SearchRepositoryResponse =
                githubApi.searchRepositories(query = query, page = page, size = params.loadSize)

            // Extracts the list of repositories from the API response
            val repositories = response.items

            // Returns a successful page load result with the data, previous key, and next key
            LoadResult.Page(
                data = repositories, // The list of repositories loaded
                prevKey = if (page == FIRST_PAGE) null else page - 1, // Previous key for paging
                nextKey = if (repositories.isEmpty()) null else page + 1 // Next key for paging
            )
        } catch (e: IOException) {
            // Returns an error result if an IOException occurs during the load
            LoadResult.Error(e)
        }
    }
}

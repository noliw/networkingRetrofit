package com.example.networkingretrofit.pagination

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Annotation to generate a JSON adapter for this class
@JsonClass(generateAdapter = true)
data class Repository(
    // The unique ID of the repository
    val id: Long,
    // The name of the repository
    val name: String,
    // The owner of the repository, represented by another model class
    val owner: RepositoryOwner,
    // The description of the repository, which can be null
    val description: String?,
    // Maps the JSON field "stargazers_count" to this property
    @Json(name = "stargazers_count")
    // The number of stars the repository has (stargazers)
    val numberOfStars: Int,
    // The main programming language used in the repository, which can be null
    val language: String?,
    // Maps the JSON field "html_url" to this property
    @Json(name = "html_url")
    // The URL of the repository(html_url)
    val repoUrl: String
)

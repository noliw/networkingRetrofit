package com.example.networkingretrofit.pagination

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true) // Annotation to generate a JSON adapter for this class
data class RepositoryOwner(
    val id: Long, // The unique ID of the repository owner
    val login: String, // The username of the repository owner
    @Json(name = "avatar_url") // Maps the JSON field "avatar_url" to this property
    val profilePicUrl: String, // The URL of the owner's profile picture
    @Json(name = "html_url") // Maps the JSON field "html_url" to this property
    val profileUrl: String // The URL of the owner's profile
)
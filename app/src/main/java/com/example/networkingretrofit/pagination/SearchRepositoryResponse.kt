package com.example.networkingretrofit.pagination

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchRepositoryResponse(
    @Json(name = "total_count")
    val totalCount: Long,
    val items: List<Repository>
)
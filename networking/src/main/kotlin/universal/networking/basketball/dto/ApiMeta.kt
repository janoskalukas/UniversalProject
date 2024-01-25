package universal.networking.basketball.dto

import com.squareup.moshi.Json

data class ApiMeta(

    @field:Json(name = "current_page")
    val currentPage: Int,

    @field:Json(name = "next_page")
    val nextPage: Int,

    @field:Json(name = "per_page")
    val perPage: Int
)
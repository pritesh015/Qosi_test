package com.example.qosi_test.models

import com.squareup.moshi.Json

data class ResponseUserDetail(

    @field:Json(name="results")
    val results: List<ResponseUser>? = null
)

data class ResponseUser(
    @field:Json(name="name")
    val name: ResponseName? = null,

    @field:Json(name="email")
    val email: String? = null,

    @field:Json(name="picture")
    val picture: ResponsePicture? = null
)

data class ResponseName(
    @field:Json(name="first")
    val first: String? = null,

    @field:Json(name="last")
    val last: String? = null
)

data class ResponsePicture(
    @field:Json(name="large")
    val largePicture: String? = null,

    @field:Json(name="medium")
    val mediumPicture: String? = null,

    @field:Json(name="thumbnail")
    val thumbnailPicture: String? = null
)
package com.example.canyou.pojo

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("AvatarUrl")
    val avatarUrl: String? = "",
    val followers: List<Any?>? = listOf(),
    val following: List<Any?>? = listOf(),
    val fullName: String? = "",
    @SerializedName("_id")
    val id: String? = "user"
)
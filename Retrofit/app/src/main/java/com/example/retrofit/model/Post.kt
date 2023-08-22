package com.example.retrofit.model

import com.google.gson.annotations.SerializedName

data class Post(
    val userId: Int,
    val title: String,
    @SerializedName("body") val text: String
) {
    val id: Int = 0
}

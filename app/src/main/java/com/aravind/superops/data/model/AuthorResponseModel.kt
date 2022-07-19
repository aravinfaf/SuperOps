package com.aravind.superops.data.model

import com.google.gson.annotations.SerializedName

data class AuthorResponseModel(
    @SerializedName("count") val count: Int,
    @SerializedName("messages") val messages: List<Message>,
    @SerializedName("pageToken") val pageToken: String
)

data class Message(
    @SerializedName("author") val author: Author,
    @SerializedName("content") val content: String,
    @SerializedName("id") val id: Int,
    @SerializedName("updated") val updated: String
)

data class Author(
    @SerializedName("name") val name: String,
    @SerializedName("photoUrl") val photoUrl: String
)
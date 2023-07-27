package com.example.chapter14.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UserDTO (
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("items")
    val items: List<User>
)

@Parcelize
data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val username: String,
): Parcelable
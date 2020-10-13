package com.jesusbadenas.kotlin_clean_architecture_project.data.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserData(
    @SerializedName("id") val userId: Int,
    @SerializedName("cover_url") var coverUrl: String? = null,
    @SerializedName("full_name") var fullName: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("followers") var followers: Int? = 0
)

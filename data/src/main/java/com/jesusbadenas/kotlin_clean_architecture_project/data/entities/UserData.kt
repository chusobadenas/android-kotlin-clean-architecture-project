package com.jesusbadenas.kotlin_clean_architecture_project.data.entities

import com.google.gson.annotations.SerializedName

/**
 * User used in the data layer.
 */
data class UserData(
    @SerializedName("id") val userId: Int,
    @SerializedName("cover_url") var coverUrl: String? = null,
    @SerializedName("full_name") var fullName: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("followers") var followers: Int = 0
)

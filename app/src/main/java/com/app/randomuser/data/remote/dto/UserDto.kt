package com.app.randomuser.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseRandomData(
    @SerializedName("results")
    val results: List<UserDto>
)

data class NameDto(
    @SerializedName("title") val title: String,
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String

) {
    fun getFullName(): String {
        return "$title $first $last"
    }
}

data class PictureDto(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumbnail: String,
)

data class UserDto(
    @SerializedName("gender") val gender: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,

    @SerializedName("name") val name: NameDto? = null,
    @SerializedName("picture") val pictureDto: PictureDto? = null


)


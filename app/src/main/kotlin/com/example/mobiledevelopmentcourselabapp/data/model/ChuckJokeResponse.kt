package com.example.mobiledevelopmentcourselabapp.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ChuckJokeResponse(
    @SerializedName("icon_url") val iconUrl: String?,
    val id: String?,
    val url: String?,
    val value: String?,
    @SerializedName("updated_at") val updateTime: String?
)
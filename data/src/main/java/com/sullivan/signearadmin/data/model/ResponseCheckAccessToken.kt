package com.sullivan.signearadmin.data.model

import com.google.gson.annotations.SerializedName

data class ResponseCheckAccessToken(
    @SerializedName("isValidToken")
    val result: Boolean
)
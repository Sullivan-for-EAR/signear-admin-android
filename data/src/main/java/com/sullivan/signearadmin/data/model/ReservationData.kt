package com.sullivan.signearadmin.data.model

import com.google.gson.annotations.SerializedName

data class ReservationData(
    @SerializedName("rsID")
    val id: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("area")
    val center: String,
    @SerializedName("address")
    val place: String,
    @SerializedName("method")
    val method: Int,
    @SerializedName("status")
    val status: Int,
    @SerializedName("request")
    val request: String,
    @SerializedName("customerUser")
    val customerUser: CustomerInfo,
)

data class CustomerInfo(
    @SerializedName("customerID")
    val id: Int,
    @SerializedName("phone")
    val phone: String,
)


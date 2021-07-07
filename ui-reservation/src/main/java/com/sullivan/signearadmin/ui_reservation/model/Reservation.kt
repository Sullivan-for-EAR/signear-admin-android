package com.sullivan.signearadmin.ui_reservation.model

import androidx.annotation.Keep
import com.sullivan.signearadmin.ui_reservation.state.ReservationState

@Keep
data class Reservation(
    val id: Int = 0,
    val date: String,
    val startTime: String,
    val endTime: String,
    val center: String,
    val place: String,
    val purpose: String,
    val isContactless: Boolean = false,
    var currentState: ReservationState = ReservationState.NotRead,
    var reject_cancel_reason: String = "",
    val isEmergency: Boolean = false
)

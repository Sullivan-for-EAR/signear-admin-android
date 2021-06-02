package com.sullivan.signearadmin.ui_reservation.model

import com.sullivan.signearadmin.ui_reservation.state.ReservationState

data class NormalReservation(
    val id: Int = 0,
    val date: String,
    val startTime: String,
    val endTime: String,
    val center: String,
    val place: String,
    val purpose: String,
    val isContactless: Boolean = false,
    var currentState: ReservationState = ReservationState.NotRead,
    val userInfo: User = User(),
    var reject_cancel_reason: String = "",
) : ReservationType {
    data class User(
        val id: Int = 0,
        val name: String = "",
        val phone: String = ""
    )
}

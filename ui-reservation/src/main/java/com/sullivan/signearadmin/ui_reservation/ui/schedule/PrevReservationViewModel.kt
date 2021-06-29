package com.sullivan.signearadmin.ui_reservation.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sullivan.signearadmin.domain.SignearRepository
import com.sullivan.signearadmin.ui_reservation.model.Reservation
import com.sullivan.signearadmin.ui_reservation.state.ReservationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrevReservationViewModel @Inject
constructor(private val repository: SignearRepository) : ViewModel() {

    private var prevReservations = mutableListOf(
//        Reservation(
//            1,
//            "4월 30일(금)",
//            "오전 10시",
//            "오전 12시",
//            "강남구",
//            "서초좋은병원서초좋은병원서초좋은병원서초좋은병원",
//            "",
//            false,
//            ReservationState.Cancel("reason")
//        ),
        Reservation(
            2,
            "4월 30일(금)",
            "오전 8시",
            "오전 10시",
            "강남구", "중랑좋은병원", "",
            false,
            ReservationState.Cancel("reason"),
            "reason",
            true
        ),
        Reservation(
            3,
            "4월 30일(금)",
            "오전 10시",
            "오전 12시",
            "강남구", "서초좋은병원", "",
            false,
            ReservationState.Reject("reason")
        ),
        Reservation(
            4,
            "4월 30일(금)",
            "오전 10시",
            "오전 12시",
            "강남구",
            "서초좋은병원",
            "",
            false,
            ReservationState.Served
        ),
        Reservation(
            5,
            "4월 30일(금)",
            "오전 10시",
            "오전 12시",
            "강남구",
            "서초좋은병원",
            "",
            false,
            ReservationState.Cancel("reason")
        ),
    )

    fun fetchPrevList() = prevReservations
}
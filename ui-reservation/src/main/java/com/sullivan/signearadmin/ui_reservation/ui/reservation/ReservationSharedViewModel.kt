package com.sullivan.signearadmin.ui_reservation.ui.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sullivan.signearadmin.domain.SignearRepository
import com.sullivan.signearadmin.ui_reservation.model.EmergencyReservation
import com.sullivan.signearadmin.ui_reservation.model.NormalReservation
import com.sullivan.signearadmin.ui_reservation.model.Reservation
import com.sullivan.signearadmin.ui_reservation.model.ReservationType
import com.sullivan.signearadmin.ui_reservation.state.ReservationConfirmDialogState
import com.sullivan.signearadmin.ui_reservation.state.ReservationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ReservationSharedViewModel @Inject
constructor(private val repository: SignearRepository) : ViewModel() {

    private val _reservationDate = MutableStateFlow<Calendar>(Calendar.getInstance())
    val reservationDate: StateFlow<Calendar> = _reservationDate

    private val _requestCallPermission = MutableStateFlow(false)
    val requestCallPermission: StateFlow<Boolean> = _requestCallPermission

    private val reservationStartTime = MutableStateFlow("")
    private val reservationEndTime = MutableStateFlow("")
    private val reservationTime = MutableStateFlow("")
    private val reservationCenter = MutableStateFlow("")
    private val reservationPlace = MutableStateFlow("")
    private val reservationTranslationInfo = MutableStateFlow(false)
    private val reservationPurpose = MutableStateFlow("")

    private val _confirmDialogState = MutableLiveData<ReservationConfirmDialogState>()
    val confirmDialogState: LiveData<ReservationConfirmDialogState> = _confirmDialogState

    private val _reservationTotalInfo = MutableLiveData<ReservationType?>()
    val reservationTotalInfo: LiveData<ReservationType?> = _reservationTotalInfo

    private var reservationList = emptyList<ReservationType>()
    private var prevReservationList: MutableList<ReservationType> = mutableListOf(
        EmergencyReservation(
            1,
            "4월 30일(금)",
            "오전 10시",
            "오전 12시",
            "강남구",
            "중랑좋은병원",
            "",
            false,
            ReservationState.NotConfirm,
            ""
        ),
        NormalReservation(
            2,
            "4월 30일(금)",
            "오전 8시",
            "오전 10시",
            "강남구", "중랑좋은병원", "",
            false,
            ReservationState.NotConfirm
        ),
        NormalReservation(
            3,
            "4월 30일(금)",
            "오전 10시",
            "오전 12시",
            "강남구", "서초좋은병원", "",
            false,
            ReservationState.Reject("reason"),
            NormalReservation.User()
        ),
        NormalReservation(
            4,
            "4월 30일(금)",
            "오전 10시",
            "오전 12시",
            "강남구",
            "서초좋은병원",
            "",
            false,
            ReservationState.Confirm
        ),
        NormalReservation(
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
        NormalReservation(
            6,
            "4월 30일(금)", "오전 10시",
            "오전 12시", "강남구", "서초좋은병원", ""
        ),
        NormalReservation(
            7,
            "4월 30일(금)", "오전 10시",
            "오전 12시", "강남구", "서초좋은병원", ""
        ),
        NormalReservation(
            8,
            "4월 30일(금)", "오전 10시",
            "오전 12시", "강남구", "서초좋은병원", ""
        )
    )

    fun updateDate(current: Calendar) {
        _reservationDate.value = current
    }

    fun updateStartTime(startTime: String) {
        reservationStartTime.value = startTime
    }

    fun updateEndTime(endTime: String) {
        reservationEndTime.value = endTime
    }

    fun updateCenterInfo(centerInfo: String) {
        reservationCenter.value = centerInfo
    }

    fun updatePlaceInfo(placeInfo: String) {
        reservationPlace.value = placeInfo
    }

    fun updateTranslationInfo(isContactless: Boolean) {
        reservationTranslationInfo.value = isContactless
    }

    fun updatePurpose(purposeInfo: String) {
        reservationPurpose.value = purposeInfo
    }

    fun updateDialogStatus(status: ReservationConfirmDialogState) {
        _confirmDialogState.value = status
    }

    fun fetchReservationTime(): String {
        reservationTime.value = "${reservationStartTime.value}부터 ${reservationEndTime.value}까지"
        return reservationTime.value
    }

    fun assembleReservationInfo() {
//        val calendar = _reservationDate.value
//        val currentDate =
//            "${calendar.get(Calendar.MONTH) + 1}월 ${calendar.get(Calendar.DAY_OF_MONTH)}일 ${
//                getCurrentDayOfName(calendar)
//            }"
//
//        val currentReservation = Reservation(
//            0,
//            currentDate,
//            reservationStartTime.value,
//            reservationEndTime.value,
//            reservationCenter.value,
//            reservationPlace.value,
//            reservationPurpose.value,
//            reservationTranslationInfo.value
//        )
//
//        _reservationTotalInfo.value = currentReservation
//        Timber.d("${reservationTotalInfo.value}")
    }


    fun getCurrentDayOfName(calendar: Calendar): String {
        val date = calendar.time
        val simpleDateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        return simpleDateFormat.format(date)
    }

    fun clearPrevData() {
        _reservationTotalInfo.value = null
        reservationStartTime.value = ""
        reservationEndTime.value = ""
        reservationTime.value = ""
        reservationCenter.value = ""
        reservationPlace.value = ""
        reservationTranslationInfo.value = false
        reservationPurpose.value = ""
    }

    fun updateReservationList(list: List<ReservationType>) {
        reservationList = list
    }

    fun findItemWithId(id: Int) = prevReservationList.find {
        it is NormalReservation && it.id == id
    } as NormalReservation?

    fun updatePrevReservationList(list: MutableList<ReservationType>) {
        prevReservationList = list
    }

//    fun findItemWithIdInPrevList(id: Int) = prevReservationList.find { it.id == id }

    fun updateRequestCallPermission(status: Boolean) {
        _requestCallPermission.value = status
    }
}
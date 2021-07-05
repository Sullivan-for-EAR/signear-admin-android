package com.sullivan.signearadmin.ui_reservation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sullivan.common.ui_common.utils.SharedPreferenceManager
import com.sullivan.signearadmin.data.model.ReservationData
import com.sullivan.signearadmin.domain.SignearRepository
import com.sullivan.signearadmin.ui_reservation.model.NormalReservation
import com.sullivan.signearadmin.ui_reservation.model.ReservationType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject
constructor(
    private val repository: SignearRepository,
    private val sharedPreferenceManager: SharedPreferenceManager
) : ViewModel() {

    private val _myReservationList = MutableLiveData<List<ReservationType>>()
    val myReservationList: LiveData<List<ReservationType>> = _myReservationList

    init {
        getReservationList()
    }

    fun getReservationList() {
        val id = sharedPreferenceManager.getUserId()
        viewModelScope.launch {
            repository.getReservationList(id).collect { response ->
                if (response.isNotEmpty()) {
                    _myReservationList.value = convertData(response)
                } else {
                    _myReservationList.value = emptyList()
                }
            }
        }
    }

    private fun convertData(reservationList: List<ReservationData>): List<ReservationType> {
        val myList = mutableListOf<ReservationType>()

        reservationList.forEach { data ->
            myList.add(
                NormalReservation(
                    data.id,
                    data.date,
                    data.startTime,
                    data.endTime,
                    data.center,
                    data.place,
                    data.status,
                    data.request,
                    data.method != 1,
                    NormalReservation.User(data.customerUser.id, data.customerUser.phone)
                )
            )
        }
        return myList
    }
}
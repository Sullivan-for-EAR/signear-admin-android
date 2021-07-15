package com.sullivan.signearadmin.ui_reservation.ui.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sullivan.common.ui_common.utils.SharedPreferenceManager
import com.sullivan.signearadmin.data.model.CustomerInfo
import com.sullivan.signearadmin.data.model.ReservationData
import com.sullivan.signearadmin.domain.SignearRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationSharedViewModel @Inject
constructor(
    private val repository: SignearRepository,
    private val sharedPreferenceManager: SharedPreferenceManager
) : ViewModel() {

    private val _reservationDetailInfo = MutableLiveData<ReservationData>()
    val reservationDetailInfo: LiveData<ReservationData> = _reservationDetailInfo

    private val _responseConfirmReservation = MutableLiveData<ReservationData>()
    val responseConfirmReservation: LiveData<ReservationData> = _responseConfirmReservation

    private val _responseRejectReservation = MutableLiveData<ReservationData>()
    val responseRejectReservation: LiveData<ReservationData> = _responseRejectReservation

    private val _requestCallPermission = MutableStateFlow(false)
    val requestCallPermission: StateFlow<Boolean> = _requestCallPermission

    private val customerInfo = MutableLiveData<CustomerInfo>()

    private var rejectReason = ""

    fun updateRequestCallPermission(status: Boolean) {
        _requestCallPermission.value = status
    }

    fun fetchReservationDetail(id: Int) {
        viewModelScope.launch {
            repository.getReservationDetailInfo(id).collect { response ->
                _reservationDetailInfo.value = response
            }
        }
    }

    fun updateCustomerInfo(info: CustomerInfo) {
        customerInfo.value = info
    }

    fun fetchCustomerPhoneInfo() = customerInfo.value?.phone

    fun confirmReservation(reservationId: Int) {
        viewModelScope.launch {
            repository.confirmReservation(reservationId, sharedPreferenceManager.getUserId())
                .collect { response ->
                    _responseConfirmReservation.value = response
                }
        }
    }

    fun rejectReservation(rejectReason: String) {
        viewModelScope.launch {
            repository.rejectReservation(
                _reservationDetailInfo.value!!.id,
                sharedPreferenceManager.getUserId(),
                rejectReason
            ).collect { response ->
                _responseRejectReservation.value = response
            }
        }
    }
}

package com.sullivan.signearadmin.ui_reservation.ui.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
constructor(private val repository: SignearRepository) : ViewModel() {

    private val _reservationDetailInfo = MutableLiveData<ReservationData>()
    val reservationDetailInfo: LiveData<ReservationData> = _reservationDetailInfo

    private val _requestCallPermission = MutableStateFlow(false)
    val requestCallPermission: StateFlow<Boolean> = _requestCallPermission

    private val _sendSMS= MutableLiveData(false)
    val sendSMS: MutableLiveData<Boolean> = _sendSMS

    private val customerInfo = MutableLiveData<CustomerInfo>()

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
}

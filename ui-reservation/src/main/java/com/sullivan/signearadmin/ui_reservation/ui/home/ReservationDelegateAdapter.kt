package com.sullivan.signearadmin.ui_reservation.ui.home

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sullivan.signearadmin.ui_reservation.model.ReservationType
import com.sullivan.signearreservationTotalInfo.ui_reservation.ui.reservation.ReservationSharedViewModel

class ReservationDelegateAdapter(
    reservationList: MutableList<ReservationType>,
    sharedViewModel: ReservationSharedViewModel
) :
    ListDelegationAdapter<List<ReservationType>>() {

    private val reservationItem = reservationList

    private val delegates = listOf(
        EmergencyReservationAdapterDelegate(sharedViewModel),
        NormalReservationAdapterDelegate(),
    )

    init {
        delegates.forEach { delegate -> delegatesManager.addDelegate(delegate) }
        setItems(reservationList)
    }


}
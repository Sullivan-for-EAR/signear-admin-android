package com.sullivan.signearadmin.ui_reservation.ui.home

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sullivan.signearadmin.ui_reservation.model.ReservationType

class ReservationDelegateAdapter(reservationList: MutableList<ReservationType>) :
    ListDelegationAdapter<List<ReservationType>>() {

    private val reservationItem = reservationList

    private val delegates = listOf(
        EmergencyReservationAdapterDelegate(),
        NormalReservationAdapterDelegate(),
    )

    init {
        delegates.forEach { delegate -> delegatesManager.addDelegate(delegate) }
        setItems(reservationList)
    }


}
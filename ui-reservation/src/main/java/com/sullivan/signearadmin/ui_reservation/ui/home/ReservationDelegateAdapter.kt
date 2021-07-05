package com.sullivan.signearadmin.ui_reservation.ui.home

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sullivan.signearadmin.ui_reservation.model.ReservationType

class ReservationDelegateAdapter(
    reservationList: MutableList<ReservationType>,
    sharedViewModel: HomeViewModel,
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

    fun addAll(newReservationList: MutableList<ReservationType>) {
        reservationItem.clear()
        reservationItem.addAll(newReservationList)
        setItems(reservationItem)
        notifyDataSetChanged()
    }
}
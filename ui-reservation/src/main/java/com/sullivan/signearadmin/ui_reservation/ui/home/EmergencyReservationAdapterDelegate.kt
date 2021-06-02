package com.sullivan.signearadmin.ui_reservation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.sullivan.sigenearadmin.ui_reservation.databinding.ItemEmergencyReservationBinding
import com.sullivan.signearadmin.ui_reservation.model.EmergencyReservation
import com.sullivan.signearadmin.ui_reservation.model.ReservationType

class EmergencyReservationAdapterDelegate(private val requestPermission: () -> Unit) :
    AdapterDelegate<List<ReservationType>>() {

    private lateinit var bindingItem: ItemEmergencyReservationBinding

    override fun isForViewType(items: List<ReservationType>, position: Int): Boolean {
        return items[position] is EmergencyReservation
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        bindingItem = ItemEmergencyReservationBinding.inflate(layoutInflater)
        return EmergencyReservationListViewHolder(bindingItem)
    }

    override fun onBindViewHolder(
        items: List<ReservationType>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as EmergencyReservation
        (holder as EmergencyReservationListViewHolder).bind(item)
    }

    inner class EmergencyReservationListViewHolder(private val binding: ItemEmergencyReservationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EmergencyReservation) {
            binding.apply {
                btnConfirm.setOnClickListener {
                    requestPermission()
                }
            }
        }
    }
}
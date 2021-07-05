package com.sullivan.signearadmin.ui_reservation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.sullivan.common.ui_common.ex.convertDate
import com.sullivan.common.ui_common.ex.getTimeInfo
import com.sullivan.sigenearadmin.ui_reservation.databinding.ItemReservationBinding
import com.sullivan.signearadmin.ui_reservation.model.NormalReservation
import com.sullivan.signearadmin.ui_reservation.model.ReservationType
import com.sullivan.signearadmin.ui_reservation.ui.reservation.ReservationInfoActivity.Companion.newIntent

class NormalReservationAdapterDelegate : AdapterDelegate<List<ReservationType>>() {

    private lateinit var bindingItem: ItemReservationBinding

    override fun isForViewType(items: List<ReservationType>, position: Int): Boolean {
        return items[position] is NormalReservation
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        bindingItem = ItemReservationBinding.inflate(layoutInflater)
        return ReservationListViewHolder(bindingItem)
    }

    override fun onBindViewHolder(
        items: List<ReservationType>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as NormalReservation
        (holder as ReservationListViewHolder).bind(item)
    }

    inner class ReservationListViewHolder(private val binding: ItemReservationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NormalReservation) {
            binding.apply {
                tvDate.text = item.date.convertDate()
                tvReservationStartTime.text = item.startTime.getTimeInfo()
                tvReservationEndTime.text = item.endTime.getTimeInfo()
                tvPlace.text = item.place
                tvTranslation.text = if (!item.isContactless) "수어 통역" else "화상 통역"
                btnDetail.setOnClickListener {
                    it.context.startActivity(newIntent(it.context, "reservationList", item.id))
                }
            }
        }
    }
}

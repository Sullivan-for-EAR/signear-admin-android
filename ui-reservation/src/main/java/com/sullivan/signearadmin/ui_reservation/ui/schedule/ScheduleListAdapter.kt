package com.sullivan.signearadmin.ui_reservation.ui.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sullivan.common.ui_common.ex.convertDate
import com.sullivan.common.ui_common.ex.getTimeInfo
import com.sullivan.sigenearadmin.ui_reservation.R
import com.sullivan.sigenearadmin.ui_reservation.databinding.ItemScheduleBinding
import com.sullivan.signearadmin.ui_reservation.model.NormalReservation
import com.sullivan.signearadmin.ui_reservation.ui.reservation.ReservationInfoActivity.Companion.newIntent

class ScheduleListAdapter(private val scheduleList: MutableList<NormalReservation>) :
    RecyclerView.Adapter<ScheduleListViewHolder>() {
    private lateinit var binding: ItemScheduleBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemScheduleBinding.inflate(layoutInflater)
        return ScheduleListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleListViewHolder, position: Int) {
        val item = scheduleList[position]
        holder.bind(item)
    }

    override fun getItemCount() = scheduleList.size

    fun addAll(newList: MutableList<NormalReservation>) {
        scheduleList.clear()
        scheduleList.addAll(newList)
        notifyDataSetChanged()
    }
}

class ScheduleListViewHolder(private val binding: ItemScheduleBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NormalReservation) {
        binding.apply {
            tvDate.text = item.date.convertDate()
            tvStartTime.text = item.startTime.getTimeInfo()
            tvEndTime.text = item.endTime.getTimeInfo()
            tvPlace.text = item.place
            tvRequest.text = item.purpose
            if (!item.isContactless) {
                ivTranslation.setBackgroundResource(R.drawable.sign_translation_icon)
            } else {
                ivTranslation.setBackgroundResource(R.drawable.online_translation_icon)
            }
            rootView.setOnClickListener {
                it.context.startActivity(newIntent(it.context, "scheduleList", item.id))
            }
        }
    }
}
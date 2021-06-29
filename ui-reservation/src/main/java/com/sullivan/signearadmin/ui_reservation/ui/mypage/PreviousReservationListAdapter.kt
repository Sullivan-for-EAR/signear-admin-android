package com.sullivan.signearadmin.ui_reservation.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.sullivan.common.ui_common.ex.makeGone
import com.sullivan.sigenearadmin.ui_reservation.R
import com.sullivan.sigenearadmin.ui_reservation.databinding.ItemPrevReservationBinding
import com.sullivan.signearadmin.ui_reservation.model.Reservation
import com.sullivan.signearadmin.ui_reservation.state.ReservationState
import com.sullivan.signearadmin.ui_reservation.ui.reservation.ReservationInfoActivity.Companion.newIntent

class PreviousReservationListAdapter(
    private val reservationList: MutableList<Reservation>
) :
    RecyclerView.Adapter<PreviousReservationListAdapter.ReservationListViewHolder>() {
    private lateinit var bindingItem: ItemPrevReservationBinding

    inner class ReservationListViewHolder(private val binding: ItemPrevReservationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Reservation) {
            binding.apply {
                if (item.isEmergency) {
                    tvPlace.text = R.string.fragment_emergency_reservation_title.toString()
                } else {
                    tvPlace.text = item.place
                }

                "${item.date} ${item.startTime}".also { tvDate.text = it }
                showReservationState(item.currentState, ivState)

                rvReservation.setOnClickListener {
                    it.context.startActivity(
                        newIntent(
                            it.context,
                            "prevReservationList",
                            item.id
                        )
                    )
                }

            }
        }

        private fun showReservationState(currentState: ReservationState, ivState: ImageView) {
            when (currentState) {
                is ReservationState.Served -> ivState.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        ivState.context.resources,
                        R.drawable.served_icon, null
                    )
                )
                is ReservationState.Cancel -> ivState.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        ivState.context.resources,
                        R.drawable.cancel_icon, null
                    )
                )
                is ReservationState.Reject -> ivState.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        ivState.context.resources,
                        R.drawable.reject_icon, null
                    )
                )
                else -> ivState.makeGone()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PreviousReservationListAdapter.ReservationListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        bindingItem = ItemPrevReservationBinding.inflate(layoutInflater)
        return ReservationListViewHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: ReservationListViewHolder, position: Int) {
        val item = reservationList[position]
        holder.bind(item)
    }

    override fun getItemCount() = reservationList.size

    override fun getItemId(position: Int) = reservationList[position].id.toLong()
}
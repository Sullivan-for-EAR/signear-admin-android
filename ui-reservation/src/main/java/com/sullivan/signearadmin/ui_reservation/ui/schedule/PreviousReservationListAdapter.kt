package com.sullivan.signearadmin.ui_reservation.ui.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.sullivan.common.ui_common.ex.convertDate
import com.sullivan.common.ui_common.ex.getTimeInfo
import com.sullivan.common.ui_common.ex.makeGone
import com.sullivan.sigenearadmin.ui_reservation.R
import com.sullivan.sigenearadmin.ui_reservation.databinding.ItemPrevReservationBinding
import com.sullivan.signearadmin.ui_reservation.model.NormalReservation
import com.sullivan.signearadmin.ui_reservation.state.ReservationState
import com.sullivan.signearadmin.ui_reservation.ui.reservation.ReservationInfoActivity.Companion.newIntent

class PreviousReservationListAdapter(
    private val reservationList: MutableList<NormalReservation>
) :
    RecyclerView.Adapter<PreviousReservationListAdapter.ReservationListViewHolder>() {
    private lateinit var bindingItem: ItemPrevReservationBinding

    inner class ReservationListViewHolder(private val binding: ItemPrevReservationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NormalReservation) {
            convertStatus(item)
            binding.apply {
                tvPlace.text = item.place

                "${item.date.convertDate()} ${item.startTime.getTimeInfo()}".also {
                    tvDate.text = it
                }
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

        private fun convertStatus(item: NormalReservation) {
            when (item.status) {
                6 -> item.currentState = ReservationState.Cancel()
                7 -> item.currentState = ReservationState.Served
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationListViewHolder {
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

    fun addAll(newList: MutableList<NormalReservation>) {
        reservationList.clear()
        reservationList.addAll(newList)
        notifyDataSetChanged()
    }
}
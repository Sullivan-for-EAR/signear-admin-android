package com.sullivan.signearadmin.ui_reservation.ui.schedule

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sullivan.common.ui_common.base.BaseFragment
import com.sullivan.sigenearadmin.ui_reservation.databinding.FragmentScheduleBinding
import com.sullivan.signearadmin.ui_reservation.model.NormalReservation
import com.sullivan.signearadmin.ui_reservation.state.ReservationState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : BaseFragment<FragmentScheduleBinding>() {

    private lateinit var scheduleListAdapter: ScheduleListAdapter
//    private val reservationList: MutableList<NormalReservation> = mutableListOf(
//
//        NormalReservation(
//            2,
//            "4월 30일(금)",
//            "오전 10시",
//            "오전 12시",
//            "강남구", "서초좋은병원", "",
//            false,
//            ReservationState.NotConfirm
//        ),
//        NormalReservation(
//            3,
//            "4월 30일(금)",
//            "오전 10시",
//            "오전 12시",
//            "강남구", "서초좋은병원", "",
//            true,
//            ReservationState.Reject("reason"),
//            NormalReservation.User()
//        ),
//        NormalReservation(
//            4,
//            "4월 30일(금)",
//            "오전 10시",
//            "오전 12시",
//            "강남구",
//            "서초좋은병원",
//            "",
//            false,
//            ReservationState.Confirm
//        ),
//        NormalReservation(
//            5,
//            "4월 30일(금)",
//            "오전 10시",
//            "오전 12시",
//            "강남구",
//            "서초좋은병원",
//            "",
//            false,
//            ReservationState.Cancel("reason")
//        ),
//        NormalReservation(
//            6,
//            "4월 30일(금)", "오전 10시",
//            "오전 12시", "강남구", "서초좋은병원", ""
//        ),
//        NormalReservation(
//            7,
//            "4월 30일(금)", "오전 10시",
//            "오전 12시", "강남구", "서초좋은병원", ""
//        ),
//        NormalReservation(
//            8,
//            "4월 30일(금)", "오전 10시",
//            "오전 12시", "강남구", "서초좋은병원", ""
//        )
//    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleBinding.inflate(layoutInflater)
        return binding.root
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }

    override fun setupView() {
        scheduleListAdapter = ScheduleListAdapter(mutableListOf())
        with(binding) {
            with(rvScheduleList) {
                adapter = scheduleListAdapter
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            }

            ibPrevReservation.setOnClickListener {
                startActivity(Intent(requireContext(), PrevReservationListActivity::class.java))
            }
        }
    }
}
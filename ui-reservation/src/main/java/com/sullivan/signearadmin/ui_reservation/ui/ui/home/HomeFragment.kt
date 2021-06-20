package com.sullivan.signearadmin.ui_reservation.ui.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sullivan.common.ui_common.base.BaseFragment
import com.sullivan.sigenearadmin.ui_reservation.R
import com.sullivan.sigenearadmin.ui_reservation.databinding.HomeFragmentBinding
import com.sullivan.signearadmin.ui_reservation.model.EmergencyReservation
import com.sullivan.signearadmin.ui_reservation.model.NormalReservation
import com.sullivan.signearadmin.ui_reservation.model.ReservationType
import com.sullivan.signearadmin.ui_reservation.state.ReservationState
import com.sullivan.signearadmin.ui_reservation.ui.home.ReservationDelegateAdapter
import com.sullivan.signearreservationTotalInfo.ui_reservation.ui.reservation.ReservationSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    private val sharedViewModel: ReservationSharedViewModel by activityViewModels()

    private lateinit var reservationDelegateAdapter: ReservationDelegateAdapter
    private val reservationList: MutableList<ReservationType> = mutableListOf(
        EmergencyReservation(
            1,
            "4월 30일(금)",
            "오전 10시",
            "오전 12시",
            "강남구",
            "중랑좋은병원",
            "",
            false,
            ReservationState.NotConfirm,
            ""
        ),
        NormalReservation(
            2,
            "4월 30일(금)",
            "오전 10시",
            "오전 12시",
            "강남구", "서초좋은병원", "",
            false,
            ReservationState.NotConfirm
        ),
        NormalReservation(
            3,
            "4월 30일(금)",
            "오전 10시",
            "오전 12시",
            "강남구", "서초좋은병원", "",
            false,
            ReservationState.Reject("reason"),
            NormalReservation.User()
        ),
        NormalReservation(
            4,
            "4월 30일(금)",
            "오전 10시",
            "오전 12시",
            "강남구",
            "서초좋은병원",
            "",
            false,
            ReservationState.Confirm
        ),
        NormalReservation(
            5,
            "4월 30일(금)",
            "오전 10시",
            "오전 12시",
            "강남구",
            "서초좋은병원",
            "",
            false,
            ReservationState.Cancel("reason")
        ),
        NormalReservation(
            6,
            "4월 30일(금)", "오전 10시",
            "오전 12시", "강남구", "서초좋은병원", ""
        ),
        NormalReservation(
            7,
            "4월 30일(금)", "오전 10시",
            "오전 12시", "강남구", "서초좋은병원", ""
        ),
        NormalReservation(
            8,
            "4월 30일(금)", "오전 10시",
            "오전 12시", "강남구", "서초좋은병원", ""
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater)

        return binding.root
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }

    override fun setupView() {
        binding.apply {

            reservationDelegateAdapter =
                ReservationDelegateAdapter(reservationList, sharedViewModel)

            rvReservation.apply {
                setHasFixedSize(true)
                adapter = reservationDelegateAdapter
            }

            tvTitle.setOnClickListener {
                findNavController().navigate(R.id.action_global_reservationInfoFragment)
            }
        }
    }
}
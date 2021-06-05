package com.sullivan.signearadmin.ui_reservation.ui.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.sullivan.common.ui_common.base.BaseFragment
import com.sullivan.common.ui_common.ex.makeToast
import com.sullivan.sigenearadmin.ui_reservation.databinding.HomeFragmentBinding
import com.sullivan.signearadmin.ui_reservation.model.EmergencyReservation
import com.sullivan.signearadmin.ui_reservation.model.NormalReservation
import com.sullivan.signearadmin.ui_reservation.model.ReservationType
import com.sullivan.signearadmin.ui_reservation.state.ReservationState
import com.sullivan.signearadmin.ui_reservation.ui.home.MyLifecycleObserver
import com.sullivan.signearadmin.ui_reservation.ui.home.ReservationDelegateAdapter
import com.sullivan.signearreservationTotalInfo.ui_reservation.ui.reservation.ReservationSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    private val sharedViewModel: ReservationSharedViewModel by activityViewModels()

    private lateinit var observer: MyLifecycleObserver
    private val requestPermissionLauncher: ActivityResultLauncher<String> by lazy {
        requireActivity().registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                // 권한 획득 성공 시
                makeToast("Granted!")
            } else {
                // 권한 획득 거부 시
            }
        }
    }

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

        observer = MyLifecycleObserver(requireActivity())
        lifecycle.addObserver(observer)
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
        }
    }

}
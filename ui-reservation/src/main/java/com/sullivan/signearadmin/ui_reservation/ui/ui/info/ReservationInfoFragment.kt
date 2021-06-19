package com.sullivan.signearadmin.ui_reservation.ui.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.sullivan.common.ui_common.base.BaseFragment
import com.sullivan.sigenearadmin.ui_reservation.databinding.FragmentInfoBinding
import com.sullivan.signearadmin.ui_reservation.model.Reservation
import com.sullivan.signearreservationTotalInfo.ui_reservation.ui.reservation.ReservationSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationInfoFragment : BaseFragment<FragmentInfoBinding>() {

    private val viewModel: ReservationSharedViewModel by activityViewModels()
    private lateinit var currentReservationInfo: Reservation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun setupView() {
        TODO("Not yet implemented")
    }
}
package com.sullivan.signearadmin.ui_reservation.ui.reservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sullivan.sigenearadmin.ui_reservation.R
import com.sullivan.sigenearadmin.ui_reservation.databinding.ActivityReservationInfoBinding
import com.sullivan.signearadmin.ui_reservation.model.NormalReservation
import com.sullivan.signearreservationTotalInfo.ui_reservation.ui.reservation.ReservationSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationInfoBinding
    private lateinit var currentReservationInfo: NormalReservation
    private val viewModel: ReservationSharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReservationInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        binding.apply {
            val id: Int? = intent.getIntExtra(ReservationInfoFragment.ARGS_KEY, 0)
            if (id != null) {
                currentReservationInfo = viewModel.findItemWithId(id)!!
                makeReservationView()
            }
        }
    }

    private fun makeReservationView() {
        binding.apply {
            tvReservationPlace.text = currentReservationInfo.place
            tvDate.text = currentReservationInfo.date
            tvStartTime.text = currentReservationInfo.startTime
            tvEndTime.text = currentReservationInfo.endTime

            if (!currentReservationInfo.isContactless) {
                tvReservationTranslation.text =
                    getString(R.string.fragment_reservation_tv_sign_translation_title)
                tvTranslation.text = "(${getString(R.string.fragment_reservation_tv_contact_title)})"
            } else {
                tvReservationTranslation.text =
                    getString(R.string.fragment_reservation_tv_online_translation_title)
                tvTranslation.text = "(${getString(R.string.fragment_reservation_tv_online_title)})"
            }

            tvReservationPurpose.text = currentReservationInfo.purpose

            btnBack.setOnClickListener {
//                findNavController().navigate(R.id.action_reservationInfoFragment_pop)
            }

        }
    }
}
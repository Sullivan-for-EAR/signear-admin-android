package com.sullivan.signearadmin.ui_reservation.ui.schedule

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sullivan.sigenearadmin.ui_reservation.databinding.ActivityPreviousReservationBinding
import com.sullivan.signearadmin.ui_reservation.ui.mypage.PreviousReservationListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrevReservationListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreviousReservationBinding
    private lateinit var reservationListAdapter: PreviousReservationListAdapter
    private val viewModel: PrevReservationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPreviousReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        reservationListAdapter =
            PreviousReservationListAdapter(
                viewModel.fetchPrevList()
            )

        with(binding) {
            rvReservation.apply {
                setHasFixedSize(true)
                adapter = reservationListAdapter
                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            }

            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}
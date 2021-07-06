package com.sullivan.signearadmin.ui_reservation.ui.schedule

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sullivan.common.ui_common.ex.makeGone
import com.sullivan.common.ui_common.ex.makeVisible
import com.sullivan.sigenearadmin.ui_reservation.databinding.ActivityPreviousReservationBinding
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
        observeViewModel()
    }

    private fun setupView() {
        reservationListAdapter =
            PreviousReservationListAdapter(mutableListOf())

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

    private fun observeViewModel() {
        viewModel.myPrevReservationList.observe(this, { prevReservationList ->
            if (prevReservationList.isNotEmpty()) {
                with(binding) {
                    rvReservation.makeVisible()
                    tvEmptyList.makeGone()
                    ivPrevReservation.makeGone()
                }
                reservationListAdapter.addAll(prevReservationList.asReversed().toMutableList())
            } else {
                with(binding) {
                    rvReservation.makeGone()
                    tvEmptyList.makeVisible()
                    ivPrevReservation.makeVisible()
                }
            }
        })
    }
}
package com.sullivan.signearadmin.ui_reservation.ui.schedule

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sullivan.common.ui_common.base.BaseFragment
import com.sullivan.sigenearadmin.ui_reservation.databinding.FragmentScheduleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : BaseFragment<FragmentScheduleBinding>() {

    private lateinit var scheduleListAdapter: ScheduleListAdapter
    private val viewModel: ScheduleListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleBinding.inflate(layoutInflater)
        observeViewModel()

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

    private fun observeViewModel() {
        viewModel.scheduleList.observe(viewLifecycleOwner, { scheduleList ->
            if (scheduleList.isNotEmpty()) {
                scheduleListAdapter.addAll(scheduleList.asReversed().toMutableList())
            }
        })
    }
}
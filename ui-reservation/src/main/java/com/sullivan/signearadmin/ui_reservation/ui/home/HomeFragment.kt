package com.sullivan.signearadmin.ui_reservation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sullivan.common.ui_common.base.BaseFragment
import com.sullivan.common.ui_common.ex.makeGone
import com.sullivan.common.ui_common.ex.makeVisible
import com.sullivan.sigenearadmin.ui_reservation.R
import com.sullivan.sigenearadmin.ui_reservation.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var reservationDelegateAdapter: ReservationDelegateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        observeViewModel()
        return binding.root
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }

    override fun onResume() {
        super.onResume()

        showProgressBar()
        viewModel.getReservationList()
    }

    override fun setupView() {
        with(binding) {

            reservationDelegateAdapter =
                ReservationDelegateAdapter(mutableListOf(), viewModel)

            rvReservation.apply {
                setHasFixedSize(true)
                adapter = reservationDelegateAdapter
            }
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            myReservationList.observe(viewLifecycleOwner, { myReservationList ->
                hideProgressBar()
                if (!myReservationList.isNullOrEmpty()) {
                    with(binding) {
                        rvReservation.makeVisible()
                        emptyReservationLayout.rootView.makeGone()
                    }
                    reservationDelegateAdapter.addAll(
                        myReservationList.asReversed().toMutableList()
                    )
                } else {
                    with(binding) {
                        rvReservation.makeGone()
                        emptyReservationLayout.rootView.makeVisible()
                    }
                }
            })
        }
    }

    override fun getProgressbarView(): ContentLoadingProgressBar = binding.progressbar
}
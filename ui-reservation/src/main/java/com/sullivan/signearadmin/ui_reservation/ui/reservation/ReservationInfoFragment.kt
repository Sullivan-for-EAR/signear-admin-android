package com.sullivan.signearadmin.ui_reservation.ui.reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sullivan.common.ui_common.base.BaseFragment
import com.sullivan.sigenearadmin.ui_reservation.R
import com.sullivan.sigenearadmin.ui_reservation.databinding.FragmentReservationInfoBinding
import com.sullivan.signearadmin.ui_reservation.model.NormalReservation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationInfoFragment : BaseFragment<FragmentReservationInfoBinding>() {

    private val viewModel: ReservationSharedViewModel by activityViewModels()
    private lateinit var currentReservationInfo: NormalReservation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReservationInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupObserve()
    }

    override fun setupView() {
        binding.apply {
            val id: Int? = arguments?.getInt(ARGS_KEY)
            if (id != null) {
//                currentReservationInfo = viewModel.findItemWithId(id)!!
                makeReservationView()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.clearPrevData()
    }

//    private fun setupObserve() {
//        viewModel.apply {
//            reservationTotalInfo.observe(viewLifecycleOwner, { reservationTotalInfo ->
//                reservationTotalInfo.let {
//                    if (reservationTotalInfo != null) {
//                        currentReservationInfo = reservationTotalInfo
//                        makeReservationView()
//                    }
//                }
//            })
//        }
//    }

    private fun makeReservationView() {
        binding.apply {
            tvReservationPlace.text = currentReservationInfo.place
            tvDate.text = currentReservationInfo.date
            tvStartTime.text = currentReservationInfo.startTime
            tvEndTime.text = currentReservationInfo.endTime

            if (!currentReservationInfo.isContactless) {
                tvReservationTranslation.text =
                    R.string.fragment_reservation_tv_sign_translation_title.toString()
                tvTranslation.text = "(${R.string.fragment_reservation_tv_contact_title})"
            } else {
                tvReservationTranslation.text =
                    R.string.fragment_reservation_tv_online_translation_title.toString()
                tvTranslation.text = "(${R.string.fragment_reservation_tv_online_title})"
            }

            tvReservationPurpose.text = currentReservationInfo.purpose

            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_reservationInfoFragment_pop)
            }

        }
    }

    private fun makeGray(view: TextView) {
        view.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.reservation_status_disable_color
            )
        )
    }

    private fun makeBlue(view: TextView) {
        view.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.reservation_status_enable_color
            )
        )
    }

    private fun makeRed(view: TextView) {
        view.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.btn_reservation_cancel_color
            )
        )
    }

    private fun showDialog() {
        val dialog = MaterialAlertDialogBuilder(
            requireContext(), R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog
        )
            .setTitle("거절 사유")
            .setMessage(currentReservationInfo.reject_cancel_reason)
            .setPositiveButton("확인") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()

        dialog.show()
    }

    private fun showCancelDialog() {
        val dialog = MaterialAlertDialogBuilder(
            requireContext(), R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog
        )
            .setTitle(R.string.fragment_reservation_info_dialog_reservation_cancel_title)
            .setMessage(R.string.fragment_reservation_info_dialog_reservation_cancel_body)
            .setPositiveButton(R.string.fragment_reservation_info_dialog_reservation_cancel_positive_btn_title) { dialog, _ ->
                dialog.dismiss()
                //todo 예약취소 작업 예정
                findNavController().navigate(R.id.action_reservationInfoFragment_pop)
            }
            .setNegativeButton(R.string.fragment_reservation_info_dialog_reservation_cancel_negative_btn_title) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()

        dialog.show()
    }

    companion object {
        const val ARGS_KEY = "itemId"
    }
}
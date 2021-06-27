package com.sullivan.signearadmin.ui_reservation.ui.reservation

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.sullivan.common.ui_common.ex.launchActivity
import com.sullivan.common.ui_common.ex.makeToast
import com.sullivan.common.ui_common.ex.openDialog
import com.sullivan.common.ui_common.ex.setupDialogWithAction
import com.sullivan.sigenearadmin.ui_reservation.R
import com.sullivan.sigenearadmin.ui_reservation.databinding.ActivityReservationInfoBinding
import com.sullivan.signearadmin.ui_reservation.model.NormalReservation
import com.sullivan.signearadmin.ui_reservation.ui.RealTimeReservationActivity
import com.sullivan.signearreservationTotalInfo.ui_reservation.ui.reservation.ReservationSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KFunction0

@AndroidEntryPoint
class ReservationInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationInfoBinding
    private lateinit var currentReservationInfo: NormalReservation
    private val viewModel: ReservationSharedViewModel by viewModels()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            //todo 실제 전화기에 영상통화여부 확인 필요
            supportFragmentManager.run {
                openDialog(
                    ContactCustomerDialogFragment.newInstance(),
                    "ContactCustomerDialogFragment"
                )
            }
        } else {
            makeToast("영상통화 연결을 위해서 전화 연결 권한이 필요합니다!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReservationInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        val id = intent.getIntExtra(ReservationInfoFragment.ARGS_KEY, 0)
        currentReservationInfo = viewModel.findItemWithId(id)!!
        makeReservationView()
    }

    private fun makeReservationView() {
        with(binding) {
            tvReservationPlace.text = currentReservationInfo.place
            tvDate.text = currentReservationInfo.date
            tvStartTime.text = currentReservationInfo.startTime
            tvEndTime.text = currentReservationInfo.endTime

            if (!currentReservationInfo.isContactless) {
                tvReservationTranslation.text =
                    getString(R.string.fragment_reservation_tv_sign_translation_title)
                tvTranslation.text =
                    "(${getString(R.string.fragment_reservation_tv_contact_title)})"
            } else {
                tvReservationTranslation.text =
                    getString(R.string.fragment_reservation_tv_online_translation_title)
                tvTranslation.text = "(${getString(R.string.fragment_reservation_tv_online_title)})"
            }

            tvReservationPurpose.text = currentReservationInfo.purpose

            btnBack.setOnClickListener {
//                findNavController().navigate(R.id.action_reservationInfoFragment_pop)
            }

            btnContact.setOnClickListener {
                requestPermission()
            }

            btnReject.setOnClickListener {
                supportFragmentManager.run {
                    openDialog(ReservationDeclineDialog.newInstance(), "ReservationDeclineDialog")
                }
            }

            btnApprove.setOnClickListener {
                setupDialogWithAction(
                    "예약 승인",
                    "승인",
                    "취소",
                    this@ReservationInfoActivity::approveReservation,
                    "${currentReservationInfo.date} ${currentReservationInfo.startTime} 예약을 승인하시나요?"
                )
            }
        }
    }

    private fun approveReservation() {
        launchActivity<RealTimeReservationActivity>()
    }

    private fun requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
    }
}
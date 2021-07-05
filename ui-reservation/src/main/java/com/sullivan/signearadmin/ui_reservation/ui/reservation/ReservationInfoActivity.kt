package com.sullivan.signearadmin.ui_reservation.ui.reservation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import com.sullivan.common.ui_common.ex.*
import com.sullivan.sigenearadmin.ui_reservation.R
import com.sullivan.sigenearadmin.ui_reservation.databinding.ActivityReservationInfoBinding
import com.sullivan.signearadmin.data.model.ReservationDetailInfo
import com.sullivan.signearadmin.ui_reservation.model.NormalReservation
import com.sullivan.signearadmin.ui_reservation.ui.RealTimeReservationActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ReservationInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationInfoBinding
    private lateinit var currentReservationInfo: ReservationDetailInfo
    private val viewModel: ReservationSharedViewModel by viewModels()
    private var from = ""

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
        observeViewModel()
    }

    private fun setupView() {
        val id = intent.getIntExtra(ID, 0)
        viewModel.fetchReservationDetail(id)
        intent.getStringExtra(FROM)?.let { from = it }
    }

    private fun observeViewModel() {
        viewModel.reservationDetailInfo.observe(this, { detailInfo ->
            detailInfo.let {
                currentReservationInfo = detailInfo
                makeReservationView(from)
            }
        })
    }

    private fun makeReservationView(from: String) {
        with(binding) {
            tvReservationPlace.text = currentReservationInfo.place
            tvDate.text = currentReservationInfo.date.convertDate()
            tvStartTime.text = currentReservationInfo.startTime.getTimeInfo()
            tvEndTime.text = currentReservationInfo.endTime.getTimeInfo()

            if (currentReservationInfo.method == 1) {
                tvReservationTranslation.text =
                    getString(R.string.fragment_reservation_tv_sign_translation_title)
                tvTranslation.text =
                    "(${getString(R.string.fragment_reservation_tv_contact_title)})"
            } else {
                tvReservationTranslation.text =
                    getString(R.string.fragment_reservation_tv_online_translation_title)
                tvTranslation.text = "(${getString(R.string.fragment_reservation_tv_online_title)})"
            }

            tvReservationPurpose.text = currentReservationInfo.request

            btnBack.setOnClickListener {
                finish()
            }

            when (from) {
                "scheduleList" -> {
                    with(btnContact) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            setBackgroundColor(resources.getColor(R.color.black, null))
                        } else {
                            setBackgroundColor(resources.getColor(R.color.black))

                        }
                        setTextColor(AppCompatResources.getColorStateList(context, R.color.white))
                    }
                    btnReject.makeGone()
                    btnApprove.makeGone()
                }

                "reservationList" -> {
                    with(btnContact) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            setBackgroundColor(resources.getColor(R.color.white, null))
                        } else {
                            setBackgroundColor(resources.getColor(R.color.white))
                        }
                        setTextColor(AppCompatResources.getColorStateList(context, R.color.black))
                    }
                    btnReject.makeVisible()
                    btnApprove.makeVisible()
                }

                else -> {
                    btnReject.makeGone()
                    btnApprove.makeGone()
                    btnContact.makeGone()
                }
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
        reorderToActivity<RealTimeReservationActivity>()
    }

    private fun requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
    }

    companion object {
        private const val FROM = "from"
        private const val ID = "id"

        @JvmStatic
        fun newIntent(context: Context, from: String, id: Int): Intent {
            return Intent(context, ReservationInfoActivity::class.java).apply {
                putExtra(FROM, from)
                putExtra(ID, id)
            }
        }
    }
}
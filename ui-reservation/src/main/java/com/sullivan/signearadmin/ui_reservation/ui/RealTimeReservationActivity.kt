package com.sullivan.signearadmin.ui_reservation.ui

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sullivan.common.ui_common.ex.makeToast
import com.sullivan.common.ui_common.ex.setupDialogWithAction
import com.sullivan.sigenearadmin.ui_reservation.R
import com.sullivan.sigenearadmin.ui_reservation.databinding.ActivityRealTimeReservationBinding
import com.sullivan.signearreservationTotalInfo.ui_reservation.ui.reservation.ReservationSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RealTimeReservationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRealTimeReservationBinding
    private val viewModel: ReservationSharedViewModel by viewModels()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            //todo 실제 전화기에 영상통화여부 확인 필요
            setupDialogWithAction("안내", "연결", "취소", this::makeVideoCall, "영상통화 연결을 할까요?")
        } else {
            makeToast("영상통화 연결을 위해서 전화 연결 권한이 필요합니다!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRealTimeReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_real_time_reservation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        navView.setupWithNavController(navController)
        navView.itemIconTintList = null

        setupObserve()
    }

    private fun setupObserve() {
        with(viewModel) {
            lifecycleScope.launch {
                requestCallPermission.collect { status ->
                    if (status) {
                        requestPermission()
                        updateRequestCallPermission(false)
                    }
                }
            }
        }
    }

    private fun requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
    }

    private fun makeVideoCall() {
        val intent = Intent(Intent.ACTION_CALL).run {
            data = Uri.parse("tel:01039511218")
            putExtra("videocall", true)
        }
        startActivity(intent)
    }
}
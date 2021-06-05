package com.sullivan.signearadmin.ui_reservation.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sullivan.common.ui_common.ex.makeToast
import com.sullivan.sigenearadmin.ui_reservation.R
import com.sullivan.sigenearadmin.ui_reservation.databinding.ActivityRealTimeReservationBinding
import com.sullivan.signearreservationTotalInfo.ui_reservation.ui.reservation.ReservationSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
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
            // 권한 획득 성공 시
            makeToast("Granted!")
        } else {
            // 권한 획득 거부 시
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
        viewModel.apply {
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

    fun requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
    }
}
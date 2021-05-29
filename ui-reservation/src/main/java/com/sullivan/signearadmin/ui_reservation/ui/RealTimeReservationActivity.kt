package com.sullivan.signearadmin.ui_reservation.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.sullivan.sigenearadmin.ui_reservation.R
import com.sullivan.sigenearadmin.ui_reservation.databinding.ActivityRealTimeReservationBinding

class RealTimeReservationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRealTimeReservationBinding

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

    }
}
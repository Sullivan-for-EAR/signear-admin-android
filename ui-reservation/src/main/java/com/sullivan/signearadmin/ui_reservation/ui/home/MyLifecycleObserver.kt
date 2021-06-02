package com.sullivan.signearadmin.ui_reservation.ui.home

import android.Manifest
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.sullivan.common.ui_common.ex.makeToast

/**
 * https://developer.android.com/training/basics/intents/result?hl=ko
 */
class MyLifecycleObserver(private val activity: FragmentActivity) : DefaultLifecycleObserver {
    lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        requestPermissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                // 권한 획득 성공 시
                activity.makeToast("Granted!")
            } else {
                // 권한 획득 거부 시
            }
        }
    }

    fun requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
    }
}

package com.sullivan.signearadmin.ui_reservation.ui.reservation

import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.sullivan.sigenearadmin.ui_reservation.databinding.DialogReservationDeclineBinding

class ReservationDeclineDialog : DialogFragment() {

    private lateinit var binding: DialogReservationDeclineBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogReservationDeclineBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        with(binding) {
            btnCancel.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        setLayoutSize()
    }

    private fun setLayoutSize() {
        val window = dialog?.window
        val size = Point()

        val display = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireContext().display
        } else {
            window?.windowManager?.defaultDisplay
        }

        display?.getSize(size)

        val width = size.x
        window?.setLayout((width * 0.75).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun dismiss() {
        super.dismiss()

        if (dialog != null)
            dialog!!.dismiss()

        dismissInstance()
    }

    companion object {
        private var fragment: ReservationDeclineDialog? = null

        @JvmStatic
        fun newInstance() =
            fragment ?: synchronized(this) {
                fragment ?: ReservationDeclineDialog().also { fragment = it }
            }

        @JvmStatic
        fun dismissInstance() {
            fragment = null
        }
    }
}
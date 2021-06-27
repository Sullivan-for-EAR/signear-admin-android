package com.sullivan.signearadmin.ui_reservation.ui.reservation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sullivan.sigenearadmin.ui_reservation.databinding.DialogContactCustomerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactCustomerDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DialogContactCustomerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogContactCustomerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        with(binding) {
            tvCancel.setOnClickListener {
                dismiss()
            }

            tvVideoCall.setOnClickListener {
                makeVideoCall()
            }

        }
    }

    private fun makeVideoCall() {
        val intent = Intent(Intent.ACTION_CALL).run {
            data = Uri.parse("tel:01039511218")
            putExtra("videocall", true)
        }
        startActivity(intent)
        dismiss()
    }

    override fun onResume() {
        super.onResume()

        dialog?.setOnKeyListener { _, _, keyEvent ->
            if (keyEvent.keyCode == KeyEvent.KEYCODE_BACK) {
                dismiss()
            }
            false
        }
    }

    override fun dismiss() {
        super.dismiss()

        if (dialog != null)
            dialog!!.dismiss()

        dismissInstance()
    }

    companion object {
        private var fragment: ContactCustomerDialogFragment? = null

        @JvmStatic
        fun newInstance() =
            fragment ?: synchronized(this) {
                fragment ?: ContactCustomerDialogFragment().also { fragment = it }
            }

        @JvmStatic
        fun dismissInstance() {
            fragment = null
        }
    }
}
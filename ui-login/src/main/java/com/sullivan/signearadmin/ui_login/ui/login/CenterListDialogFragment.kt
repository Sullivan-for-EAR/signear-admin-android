package com.sullivan.signearadmin.ui_login.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sullivan.signearadmin.ui_login.R
import com.sullivan.signearadmin.ui_login.databinding.DialogSpinnerViewBinding

class CenterListDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DialogSpinnerViewBinding
    private lateinit var centerArray: Array<String>
    private var selectedCenter = ""
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSpinnerViewBinding.inflate(layoutInflater)
        setupView()

        return binding.root
    }

    private fun setupView() {
        with(binding) {
            btnClose.setOnClickListener {
                   dismiss()
            }

            centerArray = resources.getStringArray(R.array.center_array)

            with(spCenter) {
                setItems(centerArray.toList())
                setOnItemSelectedListener { _, _, _, item ->
                    selectedCenter = item.toString()
                    viewModel.updateCenterInfo(selectedCenter)
                }
            }
        }

    }

    override fun dismiss() {
        super.dismiss()

        if (dialog != null)
            dialog!!.dismiss()

        dismissInstance()
    }

    companion object {
        private var fragment: CenterListDialogFragment? = null

        @JvmStatic
        fun newInstance() =
            fragment ?: synchronized(this) {
                fragment ?: CenterListDialogFragment().also { fragment = it }
            }

        @JvmStatic
        fun dismissInstance() {
            fragment = null
        }
    }
}
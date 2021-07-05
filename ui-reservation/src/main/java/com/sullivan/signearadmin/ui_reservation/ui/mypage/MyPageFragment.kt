package com.sullivan.signearadmin.ui_reservation.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sullivan.common.ui_common.base.BaseFragment
import com.sullivan.common.ui_common.navigator.LoginNavigator
import com.sullivan.common.ui_common.utils.SharedPreferenceManager
import com.sullivan.sigenearadmin.ui_reservation.R
import com.sullivan.sigenearadmin.ui_reservation.databinding.FragmentMyPageBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>() {

    @Inject
    lateinit var loginNavigator: LoginNavigator

    @Inject
    lateinit var sharedPreferenceManager: SharedPreferenceManager

    private val viewModel: MyPageViewModel by viewModels()

    private lateinit var itemArray: Array<String>
    private lateinit var itemList: List<MyPageItem>
    private lateinit var myPageListAdapter: MyPageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMyPageBinding.inflate(layoutInflater)
        observeViewModel()

        return binding.root
    }

    override fun setupView() {
        itemArray = resources.getStringArray(R.array.profile_items)
        itemList =
            listOf(MyPageItem(itemArray[0]), MyPageItem(itemArray[1]))

        binding.apply {

            myPageListAdapter = MyPageListAdapter(
                itemList,
                loginNavigator,
                requireActivity(),
                this@MyPageFragment::clearAccessToken
            )
            rvMypage.apply {
                adapter = myPageListAdapter
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            }
        }
    }

    private fun clearAccessToken() {
        with(sharedPreferenceManager) {
            setAccessToken("")
            setUserId(0)
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            userInfo.observe(viewLifecycleOwner, { userInfo ->
                with(binding) {
                    tvUserName.text = userInfo.email
                    (userInfo.address + "수어센터").also { tvCenter.text = it }
                }
            })
        }
    }
}
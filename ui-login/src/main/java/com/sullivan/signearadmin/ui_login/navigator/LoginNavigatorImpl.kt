package com.sullivan.signearadmin.ui_login.navigator

import android.content.Context
import com.sullivan.common.ui_common.ex.launchActivity
import com.sullivan.common.ui_common.navigator.LoginNavigator
import com.sullivan.signearadmin.ui_login.ui.LoginActivity

import javax.inject.Inject

class LoginNavigatorImpl @Inject constructor() : LoginNavigator {
    override fun openLogin(context: Context) {
        context.launchActivity<LoginActivity>()
    }
}
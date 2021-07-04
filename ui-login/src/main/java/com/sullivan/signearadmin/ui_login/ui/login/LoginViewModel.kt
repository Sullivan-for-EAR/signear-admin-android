package com.sullivan.signearadmin.ui_login.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sullivan.signearadmin.data.model.ResponseCheckEmail
import com.sullivan.signearadmin.domain.SignearRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject
constructor(private val repository: SignearRepository) : ViewModel() {

    private val _loginState = MutableLiveData<LoginState>(LoginState.Init)
    val loginState: LiveData<LoginState> = _loginState

    private val _resultCheckEmail = MutableLiveData<ResponseCheckEmail>()
    val resultCheckEmail: LiveData<ResponseCheckEmail> = _resultCheckEmail

    @InternalCoroutinesApi
    fun checkEmail(email: String) {
        viewModelScope.launch {
            repository.checkEmail(email).collect { result ->
                _resultCheckEmail.value = result
            }
        }
    }


    fun updateLoginState(currentState: LoginState) {
        _loginState.value = currentState
    }

    fun checkCurrentState() = _loginState.value
}
package com.sullivan.signearadmin.ui

import androidx.lifecycle.*
import com.sullivan.common.core.DataState
import com.sullivan.common.ui_common.utils.SharedPreferenceManager
import com.sullivan.signearadmin.data.model.ResponseCheckAccessToken
import com.sullivan.signearadmin.domain.SignearRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class SplashViewModel @Inject
constructor(
    private val repository: SignearRepository,
    private val sharedPreferenceManager: SharedPreferenceManager
) : ViewModel() {

    private val _resultCheckAccessToken = MutableLiveData<ResponseCheckAccessToken>()
    val resultCheckAccessToken: LiveData<ResponseCheckAccessToken> = _resultCheckAccessToken

    fun checkAccessToken() = !sharedPreferenceManager.getAccessToken().isNullOrEmpty()

    fun checkIsAccessTokenValid() {
        viewModelScope.launch {
            try {
                repository.checkAccessToken().collect {
                    Timber.d("$it")
                    _resultCheckAccessToken.value = it
                }
            } catch (e: Exception) {
                Timber.e(DataState.Error(e).toString())
            }
        }
    }
}
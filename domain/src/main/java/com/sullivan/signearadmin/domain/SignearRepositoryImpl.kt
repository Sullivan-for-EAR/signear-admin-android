package com.sullivan.signearadmin.domain

import com.sullivan.common.core.DataState
import com.sullivan.signear.data.model.ResponseLogin
import com.sullivan.signearadmin.data.model.ResponseCheckAccessToken
import com.sullivan.signearadmin.data.model.ResponseCheckEmail
import com.sullivan.signearadmin.data.remote.NetworkDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

@ViewModelScoped
@ExperimentalCoroutinesApi
class SignearRepositoryImpl
@Inject constructor(
    private val networkDataSource: NetworkDataSource
) : SignearRepository {

    override suspend fun checkEmail(email: String): Flow<ResponseCheckEmail> =
        callbackFlow {
            networkDataSource.checkEmail(email)
                .catch { exception -> Timber.e(exception) }
                .collect {
                    when (it) {
                        is DataState.Success -> {
                            trySend(it.data)
                        }
                        is DataState.Error -> {
                            Timber.e("DataState.Error")
                        }
                    }
                }
        }

    override suspend fun login(email: String, password: String): Flow<ResponseLogin> =
        callbackFlow {
            networkDataSource.login(email, password)
                .catch { exception -> Timber.e(exception) }
                .collect {
                    when (it) {
                        is DataState.Success -> {
                            trySend(it.data)
                        }
                        is DataState.Error -> {
                            Timber.e("DataState.Error")
                        }
                    }
                }
        }

    override suspend fun checkAccessToken(): Flow<ResponseCheckAccessToken> =
        callbackFlow {
            networkDataSource.checkAccessToken()
                .catch { exception -> Timber.e(exception) }
                .collect {
                    when (it) {
                        is DataState.Success -> {
                            trySend(it.data)
                        }
                        is DataState.Error -> {
                            Timber.e("DataState.Error")
                        }
                    }
                }
        }

    override suspend fun createUser(
        email: String,
        password: String,
        center: String
    ): Flow<ResponseLogin> =
        callbackFlow {
            networkDataSource.createUser(email, password, center)
                .catch { exception -> Timber.e(exception) }
                .collect {
                    when (it) {
                        is DataState.Success -> {
                            trySend(it.data)
                        }
                        is DataState.Error -> {
                            Timber.e("DataState.Error")
                        }
                    }
                }
        }
}
package com.sullivan.signearadmin.data.remote

import com.sullivan.common.core.DataState
import com.sullivan.signearadmin.data.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NetworkDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun checkEmail(email: String): Flow<DataState<ResponseCheckEmail>> =
        callbackFlow {
            trySend(DataState.Success(apiService.checkEmail(email)))
            awaitClose { close() }
        }

    suspend fun login(email: String, password: String): Flow<DataState<ResponseLogin>> =
        callbackFlow {
            trySend(
                DataState.Success(
                    apiService.login(
                        hashMapOf(
                            "email" to email,
                            "password" to password
                        )
                    )
                )
            )
            awaitClose { close() }
        }

    suspend fun checkAccessToken(): Flow<DataState<ResponseCheckAccessToken>> =
        callbackFlow {
            trySend(DataState.Success(apiService.checkAccessToken()))
            awaitClose { close() }
        }

    suspend fun createUser(
        email: String,
        password: String,
        center: String
    ): Flow<DataState<ResponseLogin>> =
        callbackFlow {
            trySend(
                DataState.Success(
                    apiService.createUser(
                        hashMapOf(
                            "email" to email,
                            "password" to password,
                            "address" to center
                        )
                    )
                )
            )
            awaitClose { close() }
        }

    suspend fun getUserInfo(id: Int): Flow<DataState<UserProfile>> =
        callbackFlow {
            trySend(
                DataState.Success(
                    apiService.getUserInfo(id)
                )
            )
            awaitClose { close() }
        }

    suspend fun getReservationList(id: Int): Flow<DataState<List<ReservationData>>> =
        callbackFlow {
            trySend(
                DataState.Success(
                    apiService.getReservationList(id)
                )
            )
            awaitClose { close() }
        }

    suspend fun getReservationDetailInfo(id: Int): Flow<DataState<ReservationData>> =
        callbackFlow {
            trySend(
                DataState.Success(
                    apiService.getReservationDetailInfo(id)
                )
            )
            awaitClose { close() }
        }

    suspend fun getScheduleList(id: Int): Flow<DataState<List<ReservationData>>> =
        callbackFlow {
            trySend(
                DataState.Success(
                    apiService.getScheduleList(id)
                )
            )
            awaitClose { close() }
        }

    suspend fun confirmReservation(
        reservationId: Int,
        id: Int
    ): Flow<DataState<ReservationData>> =
        callbackFlow {
            trySend(
                DataState.Success(
                    apiService.confirmReservation(reservationId, id)
                )
            )
            awaitClose { close() }
        }

    suspend fun rejectReservation(
        reservationId: Int, id: Int, rejectReason: String
    ): Flow<DataState<ReservationData>> =
        callbackFlow {
            trySend(
                DataState.Success(
                    apiService.rejectReservation(
                        reservationId, hashMapOf(
                            "sign_id" to id,
                            "reject" to rejectReason
                        )
                    )
                )
            )
            awaitClose { close() }
        }

    suspend fun getPrevReservationList(id: Int): Flow<DataState<List<ReservationData>>> =
        callbackFlow {
            trySend(
                DataState.Success(
                    apiService.getPrevReservationList(id)
                )
            )
            awaitClose { close() }
        }
}
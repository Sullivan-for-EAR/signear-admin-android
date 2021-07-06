package com.sullivan.signearadmin.domain

import com.sullivan.common.core.DataState
import com.sullivan.signearadmin.data.model.*
import kotlinx.coroutines.flow.Flow

interface SignearRepository {
    suspend fun checkEmail(email: String): Flow<ResponseCheckEmail>

    suspend fun login(email: String, password: String): Flow<ResponseLogin>

    suspend fun checkAccessToken(): Flow<ResponseCheckAccessToken>

    suspend fun createUser(email: String, password: String, center: String): Flow<ResponseLogin>

    suspend fun getUserInfo(id: Int): Flow<UserProfile>

    suspend fun getReservationList(id: Int): Flow<List<ReservationData>>

    suspend fun getReservationDetailInfo(id: Int): Flow<ReservationData>

    suspend fun getScheduleList(id: Int): Flow<List<ReservationData>>

    suspend fun confirmReservation(reservationId: Int, id: Int): Flow<ReservationData>

    suspend fun rejectReservation(
        reservationId: Int,
        id: Int,
        rejectReason: String
    ): Flow<ReservationData>
}
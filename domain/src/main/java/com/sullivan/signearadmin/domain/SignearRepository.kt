package com.sullivan.signearadmin.domain

import com.sullivan.common.core.DataState
import com.sullivan.signear.data.model.ResponseLogin
import com.sullivan.signearadmin.data.model.RankingInfo
import com.sullivan.signearadmin.data.model.ResponseCheckAccessToken
import com.sullivan.signearadmin.data.model.ResponseCheckEmail
import kotlinx.coroutines.flow.Flow

interface SignearRepository {
    suspend fun checkEmail(email: String): Flow<ResponseCheckEmail>

    suspend fun login(email: String, password: String): Flow<ResponseLogin>

    suspend fun checkAccessToken(): Flow<ResponseCheckAccessToken>

    suspend fun createUser(email: String, password: String, center: String): Flow<ResponseLogin>
}
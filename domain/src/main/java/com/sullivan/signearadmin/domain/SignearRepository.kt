package com.sullivan.signearadmin.domain

import com.sullivan.common.core.DataState
import com.sullivan.signearadmin.data.model.RankingInfo
import kotlinx.coroutines.flow.Flow

interface SignearRepository {
    suspend fun fetchRankInfo(): Flow<DataState<RankingInfo>>
}
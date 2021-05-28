package com.sullivan.signearadmin.data.remote

import com.sullivan.signearadmin.data.model.RankingInfo
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val BASE_URL = "https://static.wippy.io/c/nrise_data/"
    }

    @GET("nrise_data.json")
    suspend fun fetchRankInfo(): RankingInfo
}
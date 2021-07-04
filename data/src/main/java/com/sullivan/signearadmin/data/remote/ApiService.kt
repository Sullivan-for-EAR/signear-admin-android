package com.sullivan.signearadmin.data.remote

import com.sullivan.signear.data.model.ResponseLogin
import com.sullivan.signearadmin.data.model.RankingInfo
import com.sullivan.signearadmin.data.model.ResponseCheckAccessToken
import com.sullivan.signearadmin.data.model.ResponseCheckEmail
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    companion object {
        //        const val BASE_URL = "https://static.wippy.io/c/nrise_data/"
        const val BASE_URL = "http://192.168.200.105:8088/"
    }

    @GET("nrise_data.json")
    suspend fun fetchRankInfo(): RankingInfo

    @GET("sign/check")
    suspend fun checkEmail(@Query("email") email: String): ResponseCheckEmail

    @POST("sign/login")
    suspend fun login(
        @Body input: HashMap<String, Any>
    ): ResponseLogin

    @POST("user/sign/create")
    suspend fun createUser(
        @Body input: HashMap<String, Any>
    ): ResponseLogin

    @GET("sign/home")
    suspend fun checkAccessToken(): ResponseCheckAccessToken
}
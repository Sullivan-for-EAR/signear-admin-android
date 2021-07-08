package com.sullivan.signearadmin.data.remote

import com.sullivan.signearadmin.data.model.*
import retrofit2.http.*

interface ApiService {

    companion object {
        //        const val BASE_URL = "http://192.168.1.9:8088/"
//        const val BASE_URL = "http://192.168.0.2:8088/"
        const val BASE_URL = "http://10.0.2.2:8088/"
    }

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

    @GET("user/sign/")
    suspend fun getUserInfo(@Query("sign_id") id: Int): UserProfile

    @GET("/reservation/sign/list")
    suspend fun getReservationList(@Query("sign_id") id: Int): List<ReservationData>

    @GET("reservation/sign/")
    suspend fun getReservationDetailInfo(@Query("reservation_id") id: Int): ReservationData

    @GET("/reservation/sign/myList")
    suspend fun getScheduleList(@Query("sign_id") id: Int): List<ReservationData>

    @POST("/reservation/sign/confirm/{reservation_id}")
    suspend fun confirmReservation(
        @Path("reservation_id") reservationId: Int,
        @Query("sign_id") id: Int
    ): ReservationData

    @POST("/reservation/sign/reject/{reservation_id}")
    suspend fun rejectReservation(
        @Path("reservation_id") reservationId: Int,
        @Body info: HashMap<String, Any>
    ): ReservationData

    @GET("management/sign/list")
    suspend fun getPrevReservationList(@Query("sign_id") id: Int): List<ReservationData>
}
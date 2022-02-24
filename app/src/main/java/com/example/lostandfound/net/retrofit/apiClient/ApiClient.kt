package com.example.lostandfound.net.retrofit.apiClient

import android.provider.ContactsContract
import com.example.lostandfound.entity.NotificationEntity
import com.example.lostandfound.entity.User
import com.example.lostandfound.net.retrofit.model.*
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiClient {

    @POST("/registerUser")
    fun registerUser(@Body reg: RegisterRequest): Call<User>

    @POST("/loginUser")
    fun loginUser(@Body log: LoginRequest): Call<User>

    @POST("{username}/putNotification")
    fun pushNotification(@Body notif: NotificationRequest, @Path("username") username: String)
    : Call<NotificationEntity>

    @GET("/{username}/notifications")
    fun getUserNotifications(@Path("username") username: String): Call<List<NotificationEntity>>

    @GET("/notifications/{id}")
    fun getNotification(@Path("id") id: Long): Call<NotificationEntity>

    @GET("/{username}/allNotifs")
    fun getAllNotifications(@Path("username") username: String): Call<List<NotificationEntity>>

    @POST("/{id}/deleteNotif")
    fun deleteNotification(@Path("id") id: Long): Call<DeleteNotifResponse>

    @POST("/{id}/updateNotif")
    fun updateNotification( @Path("id") id: Long, @Body notif: NotificationRequest)
    : Call<NotificationEntity>

    @POST("/{username}/send-notification")
    fun sendPUSH(@Body note: Note, @Path("username") username: String): Call<User>

    @POST("/{username}/setToken")
    fun setToken(@Body token: String?, @Path("username") username: String?): Call<User>

    @GET("/{username}/token")
    fun getUserToken(@Path("username") username: String?): Call<String>

    companion object {
        val BASE_URL = "http://192.168.180.43:8080/"

        fun create(): ApiClient{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiClient::class.java)
        }
    }
}
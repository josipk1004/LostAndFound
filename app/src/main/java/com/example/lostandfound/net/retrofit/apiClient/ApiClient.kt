package com.example.lostandfound.net.retrofit.apiClient


import com.example.lostandfound.net.retrofit.model.*

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiClient {

    @POST("/registerUser")
    fun registerUser(@Body reg: RegisterRequest): Call<RegisterResponse>

    @GET("/pdjetlic2/notifications")
    fun notifications(@Body reg: NotificationsRequest): Call<NotificationsResponse>

    @POST("{username}/putNotification")
    fun pushNotification(@Body notif: NotificationRequest, @Path("username") username: String)
    : Call<NotificationResponse>

    companion object {
//        val BASE_URL = "http://10.7.242.131:8080/"
//        val BASE_URL = "http://93.142.33.134:8080/"
        val BASE_URL = "http://192.168.1.10:8080/"

        fun create(): ApiClient{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiClient::class.java)
        }
    }



}
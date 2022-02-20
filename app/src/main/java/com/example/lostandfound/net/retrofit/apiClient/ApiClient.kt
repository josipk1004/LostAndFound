package com.example.lostandfound.net.retrofit.apiClient

import com.example.lostandfound.net.retrofit.model.LoginRequest
import com.example.lostandfound.net.retrofit.model.LoginResponse
import com.example.lostandfound.entity.Data
import com.example.lostandfound.net.retrofit.model.NotificationRequest
import com.example.lostandfound.net.retrofit.model.NotificationResponse
import com.example.lostandfound.net.retrofit.model.RegisterRequest
import com.example.lostandfound.net.retrofit.model.RegisterResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiClient {

    @POST("/registerUser")
    fun registerUser(@Body reg: RegisterRequest): Call<RegisterResponse>

    @POST("/loginUser")
    fun loginUser(@Body log: LoginRequest): Call<LoginResponse>

    @POST("{username}/putNotification")
    fun pushNotification(@Body notif: NotificationRequest, @Path("username") username: String)
    : Call<NotificationResponse>


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
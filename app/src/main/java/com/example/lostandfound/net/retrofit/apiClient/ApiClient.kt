package com.example.lostandfound.net.retrofit.apiClient

import com.example.lostandfound.net.retrofit.model.LoginRequest
import com.example.lostandfound.net.retrofit.model.LoginResponse
import com.example.lostandfound.net.retrofit.model.RegisterRequest
import com.example.lostandfound.net.retrofit.model.RegisterResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiClient {

    @POST("/registerUser")
    fun registerUser(@Body reg: RegisterRequest): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("/loginUser")
    fun loginUser(@Body log: LoginRequest): Call<LoginResponse>

    companion object {
        val BASE_URL = "http://10.7.242.131:8080/"

        fun create(): ApiClient{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiClient::class.java)
        }
    }
}
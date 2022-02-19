package com.example.lostandfound.net.retrofit.apiClient

<<<<<<< HEAD
import com.example.lostandfound.net.retrofit.model.NotificationsRequest
import com.example.lostandfound.net.retrofit.model.NotificationsResponse
=======
import com.example.lostandfound.entity.Data
import com.example.lostandfound.net.retrofit.model.NotificationRequest
import com.example.lostandfound.net.retrofit.model.NotificationResponse
>>>>>>> 21bc3204e222e83f1405f202120e5d02df3bab99
import com.example.lostandfound.net.retrofit.model.RegisterRequest
import com.example.lostandfound.net.retrofit.model.RegisterResponse
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

<<<<<<< HEAD
    @GET("/pdjetlic2/notifications")
    fun notifications(@Body reg: NotificationsRequest): Call<NotificationsResponse>
=======
    @POST("{username}/putNotification")
    fun pushNotification(@Body notif: NotificationRequest, @Path("username") username: String)
    : Call<NotificationResponse>
>>>>>>> 21bc3204e222e83f1405f202120e5d02df3bab99

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
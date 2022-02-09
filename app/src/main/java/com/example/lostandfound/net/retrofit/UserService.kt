package com.example.lostandfound.net.retrofit

import com.example.lostandfound.entity.User
import retrofit.http.Body
import retrofit.http.POST

interface UserService {
    @POST("/registerUser")
    fun registerUser(@Body user: User): User?

    @POST("/loginUser")
    fun loginUser(@Body user: User): User?
}
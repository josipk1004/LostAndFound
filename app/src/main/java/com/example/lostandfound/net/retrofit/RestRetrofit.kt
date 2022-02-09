package com.example.lostandfound.net.retrofit

import com.example.lostandfound.entity.User
import com.example.lostandfound.net.RestFactory
import com.example.lostandfound.net.RestInterface
import retrofit.RestAdapter

class RestRetrofit: RestInterface {
    private val service: UserService

    init {
        val baseURL = "http://" + RestFactory.BASE_IP + ":8080/api/"
        val retrofit = RestAdapter.Builder()
            .setEndpoint(baseURL)
            .build()

        service = retrofit.create(UserService::class.java)
    }

    override fun registerUser(user: User): User? {
        return service.registerUser(user)
    }

    override fun loginUser(user: User): User? {
        return service.loginUser(user)
    }
}
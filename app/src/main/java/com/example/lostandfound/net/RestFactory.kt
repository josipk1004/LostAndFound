package com.example.lostandfound.net

import com.example.lostandfound.net.retrofit.RestRetrofit

object RestFactory {
    val BASE_IP = "10.0.2.2"

    val instance: RestInterface
        get() = RestRetrofit()
}
package com.example.lostandfound.entity

import com.example.lostandfound.net.retrofit.apiClient.ApiClient

class Data {

    companion object{
        val service = ApiClient.create()
        var loggedUser: User = User()

        fun setUser(u: User){
            loggedUser = u
        }
    }
}
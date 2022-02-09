package com.example.lostandfound.net

import com.example.lostandfound.entity.User

interface RestInterface {
    fun registerUser(user: User): User?
    fun loginUser(user: User): User?
}
package com.example.lostandfound

import java.io.Serializable

data class User(var name: String, var lastName: String, var email: String, var password: String) : Serializable {
    constructor() : this("", "", "", "") {

    }
}

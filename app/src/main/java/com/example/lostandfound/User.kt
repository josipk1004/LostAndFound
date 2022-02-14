package com.example.lostandfound

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId : Int,
    var name: String, var lastName: String, var email: String, var password: String) : Serializable {
    constructor() : this("", "", "") {

    }
}

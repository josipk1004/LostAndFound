package com.example.lostandfound.entity

import java.io.Serializable

data class ShortUser(
    var id: Long? = null,
    var name: String = ""
) : Serializable{

    constructor(user: User) : this(
        user.id,
        user.firstName + " " + user.lastName
    )

    override fun toString(): String {
        return name
    }
}

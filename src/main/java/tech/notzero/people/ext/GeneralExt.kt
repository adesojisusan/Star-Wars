package tech.notzero.people.ext

import tech.notzero.people.model.People
import tech.notzero.people.model.User

fun People.getUser():User{
    return User().apply {
        name = this@getUser.name
        height = this@getUser.height
        age = this@getUser.birthYear
    }
}
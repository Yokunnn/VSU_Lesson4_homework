package ru.zakablukov.vsu_lesson4_homework

import java.io.Serializable

data class User(
    var name: String?,
    var surname: String?,
    var age: Int?
) : Serializable

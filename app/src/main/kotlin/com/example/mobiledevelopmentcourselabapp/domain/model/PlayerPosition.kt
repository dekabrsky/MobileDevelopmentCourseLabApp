package com.example.mobiledevelopmentcourselabapp.domain.model

import java.io.Serializable

enum class PlayerPosition(val rusName: String = ""): Serializable {
    GOALKEEPER("Вратарь"),
    DEFENDER("Защитник"),
    MIDFIELD("Полузащитник"),
    FORWARD("Нападающий"),
    NONE;

    companion object {
        fun getByName(name: String) = values().find { it.name == name } ?: NONE
    }
}
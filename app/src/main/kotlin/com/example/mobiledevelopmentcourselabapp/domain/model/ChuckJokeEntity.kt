package com.example.mobiledevelopmentcourselabapp.domain.model

import org.threeten.bp.LocalDateTime

class ChuckJokeEntity(
    val value: String,
    val link: String,
    val updateTime: LocalDateTime
)
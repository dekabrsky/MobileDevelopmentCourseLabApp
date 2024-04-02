package com.example.mobiledevelopmentcourselabapp.data.mapping

import com.example.mobiledevelopmentcourselabapp.data.model.ChuckJokeResponse
import com.example.mobiledevelopmentcourselabapp.domain.model.ChuckJokeEntity
import com.example.mobiledevelopmentcourselabapp.utils.tryParseServerDateTime
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

class ChuckMapper @Inject constructor() {
    fun mapResponse(response: ChuckJokeResponse) : ChuckJokeEntity {
        return ChuckJokeEntity(
            value = response.value.orEmpty(),
            link = response.url.orEmpty(),
            updateTime = tryParseServerDateTime(response.updateTime) ?: LocalDateTime.now()
        )
    }
}
package com.example.mobiledevelopmentcourselabapp.data.repository

import com.example.mobiledevelopmentcourselabapp.data.api.ChuckApi
import com.example.mobiledevelopmentcourselabapp.data.api.NinjaApi
import com.example.mobiledevelopmentcourselabapp.data.mapping.ChuckMapper
import javax.inject.Inject

class ChuckRepository @Inject constructor(
    private val chuckApi: ChuckApi,
    private val ninjaApi: NinjaApi,
    private val mapper: ChuckMapper
) {
    fun getRandomJoke() = chuckApi.getRandomJoke().map { mapper.mapResponse(it) }

    fun getJokeByCategory(category: String) = chuckApi.getJokeByCategory(category).map { mapper.mapResponse(it) }

    fun getCategories() = chuckApi.getCategories()

    fun getJokeFromNinja() = ninjaApi.getRandomJoke()
}
package com.example.mobiledevelopmentcourselabapp.data.repository

import androidx.room.RoomDatabase
import com.example.mobiledevelopmentcourselabapp.data.database.PlayerDatabase
import com.example.mobiledevelopmentcourselabapp.data.model.PlayerDbEntity
import com.example.mobiledevelopmentcourselabapp.domain.model.PlayerPosition
import javax.inject.Inject

class PlayerRepository @Inject constructor(
    private val roomDatabase: PlayerDatabase
) {
    private val dao = roomDatabase.playerDao()

    fun getAll() = dao.getAll()

    fun addPlayer(name: String, number: Int, position: PlayerPosition) =
        dao.insert(PlayerDbEntity(name = name, number = number, position = position))
}
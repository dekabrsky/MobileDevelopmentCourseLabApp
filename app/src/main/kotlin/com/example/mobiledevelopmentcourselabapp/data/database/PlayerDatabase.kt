package com.example.mobiledevelopmentcourselabapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mobiledevelopmentcourselabapp.data.converter.PlayerPositionConverter
import com.example.mobiledevelopmentcourselabapp.data.converter.UriConverter
import com.example.mobiledevelopmentcourselabapp.data.dao.PlayerDao
import com.example.mobiledevelopmentcourselabapp.data.model.PlayerDbEntity

@Database(entities = [PlayerDbEntity::class], version = 2)
@TypeConverters(PlayerPositionConverter::class, UriConverter::class)
abstract class PlayerDatabase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}

// https://developer.android.com/training/data-storage/room/migrating-db-versions
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE players ADD COLUMN avatarUri TEXT DEFAULT NULL")
    }
}
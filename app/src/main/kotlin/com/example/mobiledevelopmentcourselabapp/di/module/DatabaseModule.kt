package com.example.mobiledevelopmentcourselabapp.di.module

import androidx.room.RoomDatabase
import com.example.mobiledevelopmentcourselabapp.data.database.PlayerDatabase
import com.example.mobiledevelopmentcourselabapp.data.provider.DatabaseProvider
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(databaseProvider: DatabaseProvider): PlayerDatabase {
        return databaseProvider.get()
    }
}
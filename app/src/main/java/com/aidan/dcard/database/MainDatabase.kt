package com.aidan.dcard.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aidan.dcard.entity.LanguageColor

@Database(
    version = 1,
    entities = [LanguageColor::class]
)
abstract class MainDatabase: RoomDatabase() {
    abstract fun provideLanguageColorDao(): LanguageColorDao
}
package com.aidan.dcard.infra.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aidan.dcard.entity.LanguageColor
import com.aidan.dcard.entity.LanguageColor.Companion.TABLE_NAME

@Dao
interface LanguageColorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(languageColor: List<LanguageColor>)

    @Query("SELECT * FROM $TABLE_NAME WHERE name IN (:names)")
    suspend fun findByNames(names: Set<String>): List<LanguageColor>

    @Query("SELECT COUNT(*) FROM $TABLE_NAME")
    suspend fun count(): Int

}
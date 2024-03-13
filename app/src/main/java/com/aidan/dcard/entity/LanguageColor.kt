package com.aidan.dcard.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aidan.dcard.entity.LanguageColor.Companion.TABLE_NAME
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = TABLE_NAME)
data class LanguageColor(
    @PrimaryKey
    val name: String,
    val color: String = "#2F81F7"
) {
    companion object {
        const val TABLE_NAME = "LanguageColor"
    }
}
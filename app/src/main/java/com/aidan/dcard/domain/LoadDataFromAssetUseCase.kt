package com.aidan.dcard.domain

import android.util.Log
import com.aidan.dcard.infra.database.LanguageColorDao
import com.aidan.dcard.entity.LanguageColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

class LoadDataFromAssetUseCase(
    private val json: Json,
    private val languageColorDao: LanguageColorDao
) {
    @OptIn(ExperimentalSerializationApi::class)
    suspend fun execute(dataInputStream: InputStream) = withContext(Dispatchers.Default) {
        if (languageColorDao.count() > 0) {
            return@withContext
        }
        val list =  try {
            json.decodeFromStream<List<LanguageColor>>(dataInputStream)
        } catch (t: Throwable) {
            Log.d("LoadDataFromAssetUseCase", t.toString())
            emptyList()
        }
        languageColorDao.insertAll(list)
    }
}
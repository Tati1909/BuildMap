package com.google.codelabs.buildyourfirstmap.place

import android.content.Context
import com.google.codelabs.buildyourfirstmap.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.io.InputStreamReader

/**
 *  Этот класс считывает список из 49 мест, которые хранятся в вызываемом файле places и возвращает их в виде файла List<Place>.
 *  Сами места представляют собой список велосипедных магазинов в Сан-Франциско, Калифорния, США.
 */
class PlacesReader(private val context: Context) {

    private val gson = Gson()

    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.places)

    fun read(): List<Place> {
        val itemType = object : TypeToken<List<PlaceResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return gson.fromJson<List<PlaceResponse>>(reader, itemType).map {
            it.toPlace()
        }
    }
}
package io.jcal.movies_provider.repository.db.converter

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class Converter {

    @TypeConverter
    fun stringListToJson(value: List<String>?): String {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = moshi.adapter<List<String>>(type)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun jsonToListString(value: String): List<String>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = moshi.adapter<List<String>>(type)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun intListToJson(value: List<Int>?): String {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val type = Types.newParameterizedType(List::class.java, Int::class.java)
        val adapter = moshi.adapter<List<Int>>(type)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun jsonToIntList(value: String): List<Int>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val type = Types.newParameterizedType(List::class.java, Int::class.java)
        val adapter = moshi.adapter<List<Int>>(type)
        return adapter.fromJson(value)
    }
}
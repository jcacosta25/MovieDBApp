package io.jcal.movies_provider.repository.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

	@TypeConverter
	fun stringListToJson(value: List<String>?): String = Gson().toJson(value)

	@TypeConverter
	fun jsonToListString(value: String): List<String>? {
		val listType = object : TypeToken<List<String>>() {}.type
		return Gson().fromJson(value, listType)
	}

	@TypeConverter
	fun intListToJson(value: List<Int>?): String = Gson().toJson(value)

	@TypeConverter
	fun jsonToIntList(value: String): List<Int>? {
		val listType = object : TypeToken<List<Int>>() {}.type
		return Gson().fromJson(value, listType)
	}
}

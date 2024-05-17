package com.yasmineraafat.mawakeet.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
	foreignKeys = [ForeignKey(
		entity = Date::class,
		childColumns = ["date_id"],
		parentColumns = ["id"])])
data class Timings (

	@PrimaryKey(autoGenerate = true)
	var id : Long = 0,

	@ColumnInfo(name = "date_id")
	var dateId: Long = 0,

	@SerializedName("Fajr") val fajr : String,

	@SerializedName("Sunrise") val sunrise : String,

	@SerializedName("Dhuhr") val dhuhr : String,

	@SerializedName("Asr") val asr : String,

	@SerializedName("Sunset") val sunset : String,

	@SerializedName("Maghrib") val maghrib : String,

	@SerializedName("Isha") val isha : String,

	@SerializedName("Imsak") val imsak : String,

	@SerializedName("Midnight") val midnight : String,

	@SerializedName("Firstthird") val firstthird : String,

	@SerializedName("Lastthird") val lastthird : String
)
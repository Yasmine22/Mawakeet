package com.yasmineraafat.mawakeet.models

import com.google.gson.annotations.SerializedName


data class Params (

	@SerializedName("Fajr") val fajr : Double,
	@SerializedName("Isha") val isha : Double
)
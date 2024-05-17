package com.yasmineraafat.mawakeet.models

import com.google.gson.annotations.SerializedName


data class Weekday (

	@SerializedName("en") val en : String,
	@SerializedName("ar") val ar : String
)
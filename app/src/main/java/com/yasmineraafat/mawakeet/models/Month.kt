package com.yasmineraafat.mawakeet.models

import com.google.gson.annotations.SerializedName


data class Month (

	@SerializedName("number") val number : Int,
	@SerializedName("en") val en : String,
	@SerializedName("ar") val ar : String
)
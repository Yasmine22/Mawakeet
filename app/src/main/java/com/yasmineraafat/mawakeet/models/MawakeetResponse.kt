package com.yasmineraafat.mawakeet.models
import com.google.gson.annotations.SerializedName




data class MawakeetResponse (

	@SerializedName("code") val code : Int,
	@SerializedName("status") val status : String,
	@SerializedName("data") val data : List<Data>
)
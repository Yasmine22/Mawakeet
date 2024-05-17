package com.yasmineraafat.mawakeet.models

import com.google.gson.annotations.SerializedName


data class Data (

	@SerializedName("timings") val timings : Timings,
	@SerializedName("date") val date : Date,
	@SerializedName("meta") val meta : Meta
)
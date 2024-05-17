
package com.yasmineraafat.mawakeet.models

import com.google.gson.annotations.SerializedName

data class Meta (

	@SerializedName("latitude") val latitude : Double,
	@SerializedName("longitude") val longitude : Double,
	@SerializedName("timezone") val timezone : String,
	@SerializedName("method") val method : Method,
	@SerializedName("latitudeAdjustmentMethod") val latitudeAdjustmentMethod : String,
	@SerializedName("midnightMode") val midnightMode : String,
	@SerializedName("school") val school : String,
	@SerializedName("offset") val offset : Offset
)
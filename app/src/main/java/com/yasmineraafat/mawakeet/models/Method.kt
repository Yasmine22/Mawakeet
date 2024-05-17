
package com.yasmineraafat.mawakeet.models

import com.google.gson.annotations.SerializedName

data class Method (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("params") val params : Params,
	@SerializedName("location") val location : Location
)
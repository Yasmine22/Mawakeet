
package com.yasmineraafat.mawakeet.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Date(

	@PrimaryKey(autoGenerate = true)
	var id: Long = 0,

	@ColumnInfo(name = "readable")
	@SerializedName("readable") val readable: String,

	@ColumnInfo(name = "timestamp")
	@SerializedName("timestamp") val timestamp: Int,

	@Ignore
    @SerializedName("gregorian") val gregorian: Gregorian?,

	@Ignore
    @SerializedName("hijri") val hijri: Hijri?
) {
	public constructor(
				id : Long = 0,

				readable : String,

				timestamp : Int,

				) : this(id,readable,timestamp,null,null) {
	}
}
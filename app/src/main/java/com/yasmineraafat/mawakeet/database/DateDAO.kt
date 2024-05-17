package com.yasmineraafat.mawakeet.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yasmineraafat.mawakeet.models.Date
import com.yasmineraafat.mawakeet.models.Timings
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface DateDAO {

    @Query("SELECT * from Date")
    fun getAllDates(): Single<List<Date>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDatesArray(arrayShows: ArrayList<Date>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDate(date: Date): Single<Long>

    @Update
    fun updateDate(date: Date): Completable

    @Query("DELETE FROM Date")
    fun deleteDate(): Completable

    @Query("SELECT * FROM Date WHERE id = :dateId")
    fun getSelectedDateById(dateId: Long): Single<Date>

    @Query("SELECT * FROM Date WHERE readable = :readable")
    fun getSelectedDateByTimestamp(readable: String): Single<Date>
}
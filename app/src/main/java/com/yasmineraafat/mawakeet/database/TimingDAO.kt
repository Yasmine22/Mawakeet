package com.yasmineraafat.mawakeet.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yasmineraafat.mawakeet.models.Timings
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface TimingDAO {

    @Query("SELECT * from Timings")
    fun getAllTimings(): Single<List<Timings>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTimingsArray(arrayShows: ArrayList<Timings>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTimings(timings: Timings): Single<Long>

    @Update
    fun updateTimings(timings: Timings): Completable

    @Query("DELETE FROM Timings")
    fun deleteTimings(): Completable

    @Query("SELECT * FROM Timings WHERE id = :timingId")
    fun getSelectedTiming(timingId: Long): Single<Timings>

    @Query("SELECT * FROM Timings WHERE date_id = :id")
    fun getSelectedTimingByDate(id: Long): Single<Timings>
}
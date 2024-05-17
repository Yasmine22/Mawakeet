package com.yasmineraafat.mawakeet.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yasmineraafat.mawakeet.models.Date
import com.yasmineraafat.mawakeet.models.Timings

@Database(entities = [Timings::class, Date::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun showTimingsDao(): TimingDAO
    abstract fun showDatesDao(): DateDAO

    companion object {
        private var INSTANCE: ApplicationDatabase? = null

        fun getInstance(context: Context): ApplicationDatabase? {
            if (INSTANCE == null) {
                synchronized(ApplicationDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ApplicationDatabase::class.java, "Mawakeet.db"
                    )
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

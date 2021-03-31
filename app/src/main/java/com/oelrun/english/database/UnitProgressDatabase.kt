package com.oelrun.english.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UnitProgress::class], version = 1, exportSchema = false)
abstract class UnitProgressDatabase: RoomDatabase() {
    abstract val unitProgressDatabaseDao: UnitProgressDatabaseDao

    companion object {
        private var INSTANCE: UnitProgressDatabase? = null

        fun getInstance(context: Context): UnitProgressDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UnitProgressDatabase::class.java,
                        "progress_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
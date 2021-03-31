package com.oelrun.english.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UnitProgressDatabaseDao {
    @Insert
    suspend fun insert(unit: UnitProgress)

    @Query("SELECT * from unit_progress_table")
    fun getAll(): LiveData<List<UnitProgress>>

    @Query("SELECT * from unit_progress_table WHERE unitId=:id")
    fun getUnit(id: Int): LiveData<UnitProgress>

    @Query("UPDATE unit_progress_table SET game_1=1 WHERE unitId=:id")
    suspend fun updateGameWords(id: Int)

    @Query("UPDATE unit_progress_table SET game_2=1 WHERE unitId=:id")
    suspend fun updateGameLetters(id: Int)

}
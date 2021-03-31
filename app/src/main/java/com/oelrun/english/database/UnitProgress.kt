package com.oelrun.english.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit_progress_table")
data class UnitProgress(
    @PrimaryKey(autoGenerate = true)
    var unitId: Int = 0,
    var unitName: String,
    var game_1: Boolean = false,
    var game_2: Boolean = false
)
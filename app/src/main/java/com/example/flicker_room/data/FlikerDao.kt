package com.example.flicker_room.data

import androidx.room.*

@Dao
interface FlikerDao {
    @Query("SELECT * FROM Flicker ORDER BY title DESC")
    fun getAllUserInfo(): List<Flicker>

    @Insert
    fun insertimg(n: Flicker)
    @Update
    fun updateimge(note: Flicker)
    @Delete
    fun deleteimg(note: Flicker)

}
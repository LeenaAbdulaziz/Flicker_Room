package com.example.flicker_room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Flicker::class],version = 1,exportSchema = false)

abstract class FlickerDatabase: RoomDatabase() {

        companion object{
            var instant: FlickerDatabase?=null
            fun getinstant(context: Context): FlickerDatabase {
                if(instant !=null)
                {
                    return instant as FlickerDatabase
                }
                instant = Room.databaseBuilder(context, FlickerDatabase::class.java,"name").run{
                    allowMainThreadQueries() }.build()
                return instant as FlickerDatabase
            }
        }
        abstract fun FlikerDao(): FlikerDao;

    }

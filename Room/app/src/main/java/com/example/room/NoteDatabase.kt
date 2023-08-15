package com.example.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNodesDao(): NoteDao

    companion object {
        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            //if the INSTANCE is null, then return it
            //if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database").build()
                INSTANCE = instance
                //return instance
                instance
            }
        }
    }
}
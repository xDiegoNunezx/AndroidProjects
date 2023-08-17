package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNote()

    @Query("SELECT * FROM note_table ORDER BY priority ASC")
    fun getAllNotes(): LiveData<MutableList<Note>>

}
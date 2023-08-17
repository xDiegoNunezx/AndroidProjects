package com.example.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    //@ColumnInfo(name = "title_note")
    val title: String,
    val description: String,
    val priority: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}

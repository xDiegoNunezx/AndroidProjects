package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.activities.AddEditActivity
import com.example.room.adaptors.NoteAdapter
import com.example.room.utils.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var notesAdaptor: NoteAdapter
    private lateinit var addNoteButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNoteButton = findViewById(R.id.add_note_button)
        recyclerView= findViewById(R.id.recycler_view)
        notesAdaptor = NoteAdapter()
        recyclerView.adapter = notesAdaptor
        recyclerView.layoutManager = LinearLayoutManager(this)

        noteViewModel = ViewModelProvider (
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[NoteViewModel::class.java]

        noteViewModel.allNotes.observe(this) {
            //here we can add the data to our recycler view
            notesAdaptor.setNotes(it)
        }

        val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Constants.REQUEST_CODE) {
                val title = it.data?.getStringExtra(Constants.EXTRA_TITLE)
                val description = it.data?.getStringExtra(Constants.EXTRA_DESCRIPTION)
                val priority = it.data?.getIntExtra(Constants.EXTRA_PRIORITY,-1)

                val note = Note(title!!, description!!, priority!!)
                noteViewModel.addNote(note)
            }
        }

        addNoteButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditActivity::class.java)
            getResult.launch(intent)
        }
    }
}
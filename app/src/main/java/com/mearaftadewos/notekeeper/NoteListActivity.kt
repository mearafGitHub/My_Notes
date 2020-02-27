package com.mearaftadewos.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*


class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        listNotes.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, DataManager.notes)

        listNotes.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(Note_position, position)
            startActivity(intent)
        }
        // to tell the adapter to keep track of changes in the number of notes in the Data
        // call the onResume mtd -> when user gets back to the note list screen/activity
        // in that mtd tell the adapter to update the list with the new note


    }

    override fun onResume() {
        super.onResume()
        (listNotes.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }

    override fun onRestart() {
        super.onRestart()
        (listNotes.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }

}

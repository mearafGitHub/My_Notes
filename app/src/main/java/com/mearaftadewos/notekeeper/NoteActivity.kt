package com.mearaftadewos.notekeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    //notePosition is the instance state of this activity
    private var notePosition = Position_Not_Set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var adapterCourses = ArrayAdapter<CourseInfo>(this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList() )
        adapterCourses.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        courseSpinner.adapter = adapterCourses

        // take the data from the extra sent from previous activity, Position_Not_Set to
        // receive the position when item is selected
        notePosition =  savedInstanceState?.getInt(Note_position, notePosition)?: // get data from saveInstance if any
            intent.getIntExtra(Note_position, Position_Not_Set) // else get data from intent
        // notePosition is not equal to -1(Position_Not_Set), means an item is selected and its position is sent
        if(notePosition != Position_Not_Set)
            displayNote()
        else {
            // if no note then add empty note and set notePosition
            DataManager.notes.add(NoteInfo()) // creates Empty NoteInfo object(has nullable parameters)
            notePosition = DataManager.notes.lastIndex
        }
    }

    private fun displayNote() {
        // point on the selected item index to get its content/info
        val note = DataManager.notes[notePosition]
        textNoteTitle.setText(note.title)
        textNoteText.setText(note.text)
        var coursePosition = DataManager.courses.values.indexOf(note.course)
        courseSpinner.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveToNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(notePosition >= DataManager.notes.lastIndex){
            // get a reference for next menu item to change it properties
            val nextMenu = menu?.findItem(R.id.action_next)
            if(nextMenu != null){
                nextMenu.icon = getDrawable(R.drawable.ic_block_white_24dp)
                nextMenu.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    private fun moveToNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }

    override fun onPause() {
        //standard App development recommends to auto save changes(note) when user leaves the activity
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        // save the content from the screen into note data
        // first ger the reference to note that the user is interacting with on this screen
        val note = DataManager.notes[notePosition]
        // then assign the values entered by the user to each properties of the original reference of that note in the DataManger class
        note.title = textNoteTitle.text.toString()
        note.text = textNoteText.text.toString()
        note.course = courseSpinner.selectedItem as CourseInfo
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(Note_position, notePosition)
    }
}

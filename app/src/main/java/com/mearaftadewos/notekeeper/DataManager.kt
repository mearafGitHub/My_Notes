package com.mearaftadewos.notekeeper

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        // take no parameters but can access the parameters of the functions created in this class
        initCourses()
        initNotes()
    }

    // functions to initialise the courses and notes data
    // by default they are public and accessible anywhere in the app
   private fun initCourses(){
        var course = CourseInfo("Android_Intents", "Android Programming with intents")
        courses.set(course.courseId, course)
        course = CourseInfo(title= "Android async programming and services", courseId= "Android Async")
        courses.set(course.courseId, course)
        course = CourseInfo("Kotlin Fundamentals", "Kotlin programming Language for Android App development")
        courses.set(course.courseId, course)
        course = CourseInfo(title= "Android App development with Fire Base", courseId= "Android Fire Base")
        courses.set(course.courseId, course)

    }

    private fun initNotes(){
        var note = NoteInfo(CourseInfo("Android", "Fragments In Android") , "Android Programming with Fragments","Single Activity Apps are good" )
        notes.add(note)
        note = NoteInfo(CourseInfo("Android with kotlin", "Coll parts of Kotlin") , "Kotlin is simple","Kotlin is elegant and typed lang" )
        notes.add(note)
        note = NoteInfo(CourseInfo("Single Activity Android Apps ", "Single Activity") , "Single Activity App with Fragments","Single Activity Apps are cool,use one Activity as an entry point." )
        notes.add(note)
        note = NoteInfo(CourseInfo("Room DB for Android", "Database in Android Apps") , "Room Database in Android Apps","Room DM Library is nearly perfect for Android app" )
        notes.add(note)
    }
}
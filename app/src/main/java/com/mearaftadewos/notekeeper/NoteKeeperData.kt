package com.mearaftadewos.notekeeper

// no body, no need for curly braces.
class CourseInfo (val courseId: String, val title: String) {
    override fun toString(): String {
        return title
    }
}

class NoteInfo ( var course: CourseInfo, title: String, text: String)
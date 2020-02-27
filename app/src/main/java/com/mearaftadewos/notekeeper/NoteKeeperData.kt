package com.mearaftadewos.notekeeper

// no body, no need for curly braces.
data class CourseInfo (val courseId: String, val title: String) {
    override fun toString(): String {
        return title
    }
}

data class NoteInfo ( var course: CourseInfo? = null, var title: String? = null, var text: String? = null)
package com.example.miaula.models

data class Course(var idCourse: Int, val school: String, var course: String?, var subject: String?, val color: Int, val students: ArrayList<Student>?)

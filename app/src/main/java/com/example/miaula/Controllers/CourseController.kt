package com.example.miaula.Controllers

import com.example.miaula.models.Course

class CourseController private constructor() {
    var courses: ArrayList<Course>? = null
    fun getCourse(idCourse: Int): Course? {
        for (x in courses!!.indices) {
            if (courses!![x].idCourse == idCourse) return courses!![x]
        }
        return null
    }

    fun addCourse(course: Course) {
        courses!!.add(course)
    } //    public void addStudent(int idCourse,Student student) {

    //        Course course = new Course();
    //        for (int x=0; x<courses.size(); x++){
    //            if (this.courses.get(x).getIdCourse() == idCourse)
    //                course = this.courses.get(x);;
    //                course.setStudent(student);
    //            System.out.println("Course..:"+ course);
    //        }
    //    }
    companion object {
        var instance: CourseController? = null
            get() {
                if (field == null) {
                    field = CourseController()
                }
                return field
            }
            private set
    }
}
package com.example.miaula.Controllers;

import com.example.miaula.Models.Course;
import com.example.miaula.Models.Student;

import java.util.ArrayList;

public class CourseController {

    private ArrayList<Course> courses;

    public CourseController() {
        courses = new ArrayList<>();
    }

    public CourseController(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void addStudent(int idCourse,Student student) {
        this.courses.get(idCourse).setStudent(student);
    }

}

package com.example.miaula.Controllers;

import com.example.miaula.Models.Course;

import java.util.ArrayList;

public class CourseController {

    private ArrayList<Course> courses;

    public CourseController() {

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
}
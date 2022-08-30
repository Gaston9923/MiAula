package com.example.miaula.Controllers;

import android.content.SharedPreferences;

import com.example.miaula.Models.Course;
import com.example.miaula.Models.Student;

import java.util.ArrayList;

public class CourseController {

    private static CourseController instance;
    private ArrayList<Course> courses;

    public static CourseController getInstance(){
        if (instance == null){
            instance = new CourseController();
        }
        return instance;
    }

    private CourseController() {

    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public Course getCourse(int idCourse){
        for (int x=0; x<courses.size(); x++){
            if (this.courses.get(x).getIdCourse() == idCourse)
                return this.courses.get(x);
        }
        return null;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void addStudent(int idCourse,Student student) {
        Course course = new Course();
        for (int x=0; x<courses.size(); x++){
            if (this.courses.get(x).getIdCourse() == idCourse)
                course = this.courses.get(x);;
                course.setStudent(student);
            System.out.println("Course..:"+ course);
        }
    }

}

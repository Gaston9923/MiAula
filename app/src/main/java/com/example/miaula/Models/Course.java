package com.example.miaula.Models;

import android.graphics.Color;

import java.util.ArrayList;

public class Course {

    private String school;
    private String course;
    private String subject;
    private int color;
    private ArrayList<Student> students;

    public Course(String school, String course, String subject, int color, ArrayList<Student> students) {
        this.school = school;
        this.course = course;
        this.subject = subject;
        this.color = color;
        this.students = students;
    }

    public Course(String school, String course, String subject, int color) {
        this.school = school;
        this.course = course;
        this.subject = subject;
        this.color = color;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}

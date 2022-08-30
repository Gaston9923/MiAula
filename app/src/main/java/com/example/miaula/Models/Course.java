package com.example.miaula.Models;

import java.util.ArrayList;

public class Course {

    private int idCourse;
    private String school;
    private String course;
    private String subject;
    private int color;
    private ArrayList<Student> students;

    public Course() {
    }

    public Course(int idCourse, String school, String course, String subject, int color) {
        this.idCourse = idCourse;
        this.school = school;
        this.course = course;
        this.subject = subject;
        this.color = color;
        this.students = new ArrayList<>();
    }

    public int getIdCourse() {
        return idCourse;
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

    public void setStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", school='" + school + '\'' +
                ", course='" + course + '\'' +
                ", subject='" + subject + '\'' +
                ", color=" + color +
                ", students=" + students +
                '}';
    }
}

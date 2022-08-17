package com.example.miaula.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miaula.Adapters.CoursesRecyclerAdapter;
import com.example.miaula.Controllers.CourseController;
import com.example.miaula.Models.Course;
import com.example.miaula.R;

import java.util.ArrayList;

public class ListCoursesFragment extends Fragment {

    private RecyclerView rvListCourses;
    private CourseController courseController;

    private CoursesRecyclerAdapter coursesRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.list_courses_fragment, container, false);
        rvListCourses = root.findViewById(R.id.rv_list_courses);
        rvListCourses.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvListCourses.setHasFixedSize(true);
        courseController = new CourseController();
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Bandera Argentina","Cuarto B","Matematicas", Color.parseColor("#FFFFFF")));
        courses.add(new Course("Don Orione","Sexto B","Lengua y Literatura",Color.parseColor("#00FFFF")));
        courses.add(new Course("Don Orione","Sexto B","Lengua y Literatura",Color.parseColor("#00FAAA")));
        courseController.setCourses(courses);
        coursesRecyclerAdapter = new CoursesRecyclerAdapter(courseController.getCourses());
        rvListCourses.setAdapter(coursesRecyclerAdapter);

        return root;
    }
}

package com.example.miaula.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miaula.Adapters.CoursesRecyclerAdapter;
import com.example.miaula.Controllers.CourseController;
import com.example.miaula.Models.Course;
import com.example.miaula.R;
import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ListCoursesFragment extends Fragment {

    private RecyclerView rvListCourses;
    private CourseController courseController;
    private ArrayList<Course> courses;

    private CoursesRecyclerAdapter coursesRecyclerAdapter;

    public ListCoursesFragment(CourseController courseController) {
        this.courseController = courseController;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.list_courses_fragment, container, false);
        rvListCourses = root.findViewById(R.id.rv_list_courses);
        rvListCourses.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvListCourses.setHasFixedSize(true);
        coursesRecyclerAdapter = new CoursesRecyclerAdapter(courseController);
        rvListCourses.setAdapter(coursesRecyclerAdapter);

        return root;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateListCourses(){
        coursesRecyclerAdapter.notifyDataSetChanged();
    }
}

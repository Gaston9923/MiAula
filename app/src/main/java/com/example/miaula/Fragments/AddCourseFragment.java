package com.example.miaula.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.miaula.Controllers.CourseController;
import com.example.miaula.MainActivity;
import com.example.miaula.Models.Course;
import com.example.miaula.R;


@SuppressLint("ValidFragment")
public class AddCourseFragment extends Fragment {

    private AddCourseFragment self = this;
    private CourseController courseController;
    private EditText etSchool;
    private EditText etCourse;
    private EditText etSubject;
    private int colorCourse;

    private Button btnGreen;
    private Button btnRed;
    private Button btnLightBlue;
    private Button btnOrange;
    private Button btnPurple;
    private Button btnAdd;

    @SuppressLint("ValidFragment")
    public AddCourseFragment(CourseController courseController) {
        this.courseController = courseController;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.add_course_fragment,container,false);
        initElements(root);
        MainActivity mainActivity = (MainActivity) getActivity();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseController.addCourse(new Course(etSchool.getText().toString(),
                                                      etCourse.getText().toString(),
                                                      etSubject.getText().toString(),colorCourse));
                getFragmentManager().beginTransaction().remove(self).commit();
                mainActivity.onResumeCourses();
            }
        });

        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCourse = R.color.green;
            }
        });
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCourse = R.color.red;
            }
        });
        btnLightBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCourse = R.color.lightblue;
            }
        });
        btnOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCourse = R.color.orange;
            }
        });
        btnPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCourse = R.color.purple;
            }
        });

        return root;
    }

    private void initElements(View root){
        etSchool = root.findViewById(R.id.et_school);
        etCourse = root.findViewById(R.id.et_course);
        etSubject = root.findViewById(R.id.et_subject);
        btnGreen = root.findViewById(R.id.btn_green);
        btnLightBlue = root.findViewById(R.id.btn_lightblue);
        btnOrange = root.findViewById(R.id.btn_orange);
        btnRed = root.findViewById(R.id.btn_red);
        btnPurple = root.findViewById(R.id.btn_purple);
        btnAdd = root.findViewById(R.id.btn_add);
        colorCourse = R.color.lightblue;
    }

}

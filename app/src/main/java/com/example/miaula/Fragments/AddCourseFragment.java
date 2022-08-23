package com.example.miaula.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.miaula.Controllers.CourseController;
import com.example.miaula.MainActivity;
import com.example.miaula.Models.Course;
import com.example.miaula.R;


@SuppressLint("ValidFragment")
public class AddCourseFragment extends Fragment {

    private AddCourseFragment self = this;
    private final CourseController courseController;
    private EditText etSchool;
    private EditText etCourse;
    private EditText etSubject;
    private TextView tvSelectColor;
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
                if (inputsValidate()){
                    AlertDialog.Builder alertBox = new AlertDialog.Builder(getContext());
                    View viewAlert = LayoutInflater.from(getContext()).inflate(R.layout.alert_new_course, null, false);
                    alertBox.setView(viewAlert);
                    Button btn_ok = viewAlert.findViewById(R.id.btn_confirm);
                    final AlertDialog d = alertBox.show();
                    btn_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            d.dismiss();
                        }
                    });
                    int id = 0;
                    courseController.addCourse(new Course(id+1,etSchool.getText().toString(),
                            etCourse.getText().toString(),
                            etSubject.getText().toString(),colorCourse));
                    getFragmentManager().beginTransaction().remove(self).commit();
                    mainActivity.onResumeCourses();
                }
            }
        });

        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnGreen, R.color.green);
            }
        });
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnRed, R.color.red);
            }
        });
        btnLightBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnLightBlue, R.color.lightblue);
            }
        });
        btnOrange.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                selectColor(btnOrange, R.color.orange);
            }
        });
        btnPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnPurple, R.color.purple);
            }
        });

        return root;
    }

    private boolean inputsValidate(){
        boolean isValid = true;
        if (etSchool.getText().toString().equals("")){
            isValid = false;
            etSchool.setError("Debe completar el campo!");
            return isValid;
        }
        if (etCourse.getText().toString().equals("")){
            etCourse.setError("Debe completar el campo!");
            isValid = false;
            return isValid;
        }
        if (etSubject.getText().toString().equals("")){
            etSubject.setError("Debe completar el campo!");
            isValid = false;
            return isValid;
        }
        return isValid;
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
        tvSelectColor = root.findViewById(R.id.tv_select_color);
        colorCourse = R.color.white;
    }

    @SuppressLint("ResourceAsColor")
    private void selectColor(Button btnColor, int color){
        btnColor.setElevation(6);
        System.out.println("color:"+color);
        tvSelectColor.setTextColor(getResources().getColor(color));
        colorCourse = color;
    }

}

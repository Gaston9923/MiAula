package com.example.miaula.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.miaula.Controllers.CourseController;
import com.example.miaula.MainActivity;
import com.example.miaula.Models.Course;
import com.example.miaula.R;
import com.google.gson.Gson;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class AddCourseFragment extends Fragment {

    private AddCourseFragment self = this;
    private final CourseController courseController;
    private ArrayList<Course> listCourses = new ArrayList<>();
    private EditText etSchool;
    private EditText etCourse;
    private EditText etSubject;
    private TextView tvSelectColor;
    private int colorCourse;

    private Button btnPink;
    private Button btnBlue;
    private Button btnYellow;
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
                    Course course = new Course(id+1,etSchool.getText().toString(),
                                                            etCourse.getText().toString(),
                                                            etSubject.getText().toString(),colorCourse);
                    courseController.addCourse(course);
                    saveInDevice(course);
                    getFragmentManager().beginTransaction().remove(self).commit();
                    mainActivity.onResumeCourses();
                }
            }
        });

        btnPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnPink, R.color.pink);
            }
        });
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnBlue, R.color.blue);
            }
        });
        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnYellow, R.color.yellow);
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

    private void saveInDevice(Course course){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        listCourses.add(course);
        String json = gson.toJson(listCourses);
        editor.putString("courses",json);
        editor.apply();
        System.out.println("Curso guardado en el dispositivo!");
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
        btnPink = root.findViewById(R.id.btn_pink);
        btnOrange = root.findViewById(R.id.btn_orange);
        btnBlue = root.findViewById(R.id.btn_blue);
        btnPurple = root.findViewById(R.id.btn_purple);
        btnYellow = root.findViewById(R.id.btn_yellow);
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

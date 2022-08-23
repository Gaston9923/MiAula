package com.example.miaula.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.miaula.Controllers.CourseController;
import com.example.miaula.Models.Student;
import com.example.miaula.R;

@SuppressLint("ValidFragment")
public class AddStudentFragment extends Fragment {

    private CourseController courseController;
    private Button btnAddStudent;
    private EditText etSurnames;
    private EditText etNames;
    private Spinner spGender;
    private String [] optionsGender;

    public AddStudentFragment(CourseController courseController) {
        this.courseController = courseController;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.add_student_fragment, container, false);
        initElements(root);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, optionsGender);
        spGender.setAdapter(adapter);
        int idStudent = 0;
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student s = new Student(idStudent+1, etSurnames.getText().toString(),
                                                             etNames.getText().toString(),
                                                             spGender.getSelectedItem().toString());
                courseController.addStudent(0,s);
                showAlertNewStudent();
            }
        });

        return root;
    }

    private void showAlertNewStudent(){
        AlertDialog.Builder alertBox = new AlertDialog.Builder(getContext());
        View viewAlert = LayoutInflater.from(getContext()).inflate(R.layout.alert_new_student, null, false);
        alertBox.setView(viewAlert);
        Button btn_ok = viewAlert.findViewById(R.id.btn_confirm);
        final AlertDialog d = alertBox.show();
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });
    }

    private void initElements(View root){
        btnAddStudent = root.findViewById(R.id.btn_new_student);
        etSurnames = root.findViewById(R.id.et_surnames);
        etNames = root.findViewById(R.id.et_names);
        spGender = root.findViewById(R.id.sp_gender);
        optionsGender = new String[]{"Masculino","Femenino"};
    }
}

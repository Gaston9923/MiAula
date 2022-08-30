package com.example.miaula.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.miaula.Controllers.CourseController;
import com.example.miaula.R;

@SuppressLint("ValidFragment")
public class MenuCourseFragment extends Fragment {

    private int idCourse;
    private CourseController courseController;
    private ConstraintLayout clMenuCourse;
    private ConstraintLayout btnTakeAssistance;
    private ConstraintLayout btnEditStudents;
    private ConstraintLayout btnConsultAbsences;
    private EditStudentsFragment editStudentsFragment;

    public MenuCourseFragment(CourseController courseController) {
        this.courseController = courseController;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.menu_course_fragment, container, false);
        this.idCourse = getArguments().getInt("idCourse");
        initElements(root);


        btnTakeAssistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnEditStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditStudentFragment();
            }
        });
        btnConsultAbsences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return root;
    }

    private void initElements(View root){
        clMenuCourse = root.findViewById(R.id.cl_edit_students);
        btnTakeAssistance = root.findViewById(R.id.btn_add_student);
        btnEditStudents = root.findViewById(R.id.btn_delete_student);
        btnConsultAbsences = root.findViewById(R.id.btn_consult_absences);
        editStudentsFragment = new EditStudentsFragment(courseController,idCourse);
    }

    private void onEditStudentFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.cl_fragment, editStudentsFragment, "EditStudentsFragment").commit();
    }

}

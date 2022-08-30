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
public class EditStudentsFragment extends Fragment {

    private int idCourse;
    private CourseController courseController;
    private ConstraintLayout clEditStudents;
    private ConstraintLayout btnAddStudent;
    private ConstraintLayout btnDeleteStudent;
    private AddStudentFragment addStudentFragment;
    private DeleteStudentFragment deleteStudentFragment;

    public EditStudentsFragment(CourseController courseController, int idCourse) {
        this.courseController = courseController;
        this.idCourse = idCourse;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.edit_course_fragment, container, false);
        initElements(root);
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddStudentFragment();
            }
        });
        btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteStudentFragment();
            }
        });

        return root;
    }

    private void onAddStudentFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.cl_fragment, addStudentFragment, "AddStudentFragment").commit();
    }

    private void onDeleteStudentFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.cl_fragment, deleteStudentFragment, "DeleteStudentFragment").commit();
    }

    private void initElements(View root){
        clEditStudents = root.findViewById(R.id.cl_edit_students);
        btnAddStudent = root.findViewById(R.id.btn_add_student);
        btnDeleteStudent = root.findViewById(R.id.btn_delete_student);
        addStudentFragment = new AddStudentFragment(courseController,idCourse);
        deleteStudentFragment = new DeleteStudentFragment(courseController,idCourse);
    }
}

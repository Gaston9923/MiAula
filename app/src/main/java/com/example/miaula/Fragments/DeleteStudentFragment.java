package com.example.miaula.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miaula.Adapters.StudentsRecyclerAdapter;
import com.example.miaula.Controllers.CourseController;
import com.example.miaula.R;

@SuppressLint("ValidFragment")
public class DeleteStudentFragment extends Fragment {

    private int idCourse;
    private CourseController courseController;
    private ConstraintLayout clDeleteStudent;
    private RecyclerView rvDeleteListStudent;
    private TextView tvNoStudents;
    private StudentsRecyclerAdapter studentsRecyclerAdapter;

    public DeleteStudentFragment(CourseController courseController,int idCourse) {
        this.courseController = courseController;
        this.idCourse = idCourse;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.delete_student_fragment, container, false);
        initElements(root);
        rvDeleteListStudent.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDeleteListStudent.setHasFixedSize(true);
        studentsRecyclerAdapter = new StudentsRecyclerAdapter(idCourse,courseController);
        rvDeleteListStudent.setAdapter(studentsRecyclerAdapter);
        showStudents();
        return root;
    }

    private void showStudents(){
        if (courseController.getCourse(idCourse).getStudents().size()==0){
            tvNoStudents.setVisibility(View.VISIBLE);
            rvDeleteListStudent.setVisibility(View.INVISIBLE);
        }else {
            tvNoStudents.setVisibility(View.INVISIBLE);
            rvDeleteListStudent.setVisibility(View.VISIBLE);
        }
    }

    private void initElements(View root){
        clDeleteStudent = root.findViewById(R.id.cl_delete_student);
        rvDeleteListStudent = root.findViewById(R.id.rv_list_delete_student);
        tvNoStudents = root.findViewById(R.id.tv_no_students);
    }
}

package com.example.miaula.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miaula.Controllers.CourseController;
import com.example.miaula.Fragments.MenuCourseFragment;
import com.example.miaula.Models.Course;
import com.example.miaula.Models.Student;
import com.example.miaula.R;

import java.util.ArrayList;

public class StudentsRecyclerAdapter extends RecyclerView.Adapter<StudentHolder> {

    private CourseController courseController;
    private ArrayList<Student> students;
    private int idCourse;
    private Context context;
    private StudentsRecyclerAdapter self = this;


    public StudentsRecyclerAdapter(int idCourse,CourseController courseController) {
        this.idCourse = idCourse;
        this.courseController = courseController;
        this.students = courseController.getCourses().get(idCourse).getStudents();
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.delete_student_container,viewGroup,false);
        context = viewGroup.getContext();
        return new StudentHolder(root, self, context);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder studentHolder, int position) {
        Student student = students.get(position);
        studentHolder.prepareView(idCourse,student, courseController);

        studentHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



    @Override
    public int getItemCount() {
        System.out.println("Cantidad de Estudiantes: "+students.size());
        return students.size();
    }
}

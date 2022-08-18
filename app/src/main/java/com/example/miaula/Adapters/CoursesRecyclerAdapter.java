package com.example.miaula.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miaula.Controllers.CourseController;
import com.example.miaula.Models.Course;
import com.example.miaula.R;

import java.util.ArrayList;

public class CoursesRecyclerAdapter extends RecyclerView.Adapter<CourseHolder> {

    private CourseController courseController;
    private ArrayList<Course> courses;
    private Context context;

    public CoursesRecyclerAdapter(CourseController courseController) {
        this.courseController = courseController;
        this.courses = courseController.getCourses();

    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_container,viewGroup,false);
        context = viewGroup.getContext();
        return new CourseHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder courseHolder, int position) {
        Course course = courses.get(position);
        courseHolder.prepareElements(course, context);

        courseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Click en el curso!");
            }
        });
    }



    @Override
    public int getItemCount() {
        System.out.println("Cantidad de Cursos: "+courses.size());
        return courses.size();
    }
}

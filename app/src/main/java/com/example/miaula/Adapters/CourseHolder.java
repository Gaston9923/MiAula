package com.example.miaula.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miaula.Models.Course;
import com.example.miaula.R;

public class CourseHolder extends RecyclerView.ViewHolder {

    private ConstraintLayout clCourse;
    private View btnOptions;
    private ImageView iconCourse;
    private TextView tvCourse;
    private TextView tvSubject;


    public CourseHolder(@NonNull View itemView) {
        super(itemView);
        initElements(itemView);
    }

    private void initElements(View root){
        clCourse = root.findViewById(R.id.cl_student);
        iconCourse = root.findViewById(R.id.icon_course);
        btnOptions = root.findViewById(R.id.btn_remove);
        tvCourse = root.findViewById(R.id.tv_surname);
        tvSubject = root.findViewById(R.id.tv_names);
    }


    @SuppressLint("ResourceType")
    public void prepareElements(Course course, Context context){
        tvCourse.setText(course.getCourse());
        tvSubject.setText(course.getSubject());

//        Drawable viewDrawable = iconCourse.getBackground();
//        viewDrawable = DrawableCompat.wrap(viewDrawable);
//        DrawableCompat.setTint(viewDrawable, course.getColor());
//        iconCourse.setBackground(viewDrawable);

        iconCourse.setBackgroundTintList(context.getResources().getColorStateList(course.getColor()));

        //Options-Course
        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
            }
        });
    }

}

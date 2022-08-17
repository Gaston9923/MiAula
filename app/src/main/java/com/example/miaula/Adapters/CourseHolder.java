package com.example.miaula.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.miaula.Models.Course;
import com.example.miaula.R;

public class CourseHolder extends RecyclerView.ViewHolder {

    private ConstraintLayout clCourse;
    private View btnOptions;
    private View iconCourse;
    private TextView tvCourse;
    private TextView tvSubject;


    public CourseHolder(@NonNull View itemView) {
        super(itemView);
        initElements(itemView);
    }

    private void initElements(View root){
        clCourse = root.findViewById(R.id.cl_course);
        iconCourse = root.findViewById(R.id.iconCourse);
        btnOptions = root.findViewById(R.id.btn_options);
        tvCourse = root.findViewById(R.id.tv_course);
        tvSubject = root.findViewById(R.id.tv_subject);
    }

    @SuppressLint("ResourceType")
    public void prepareElements(Course course, Context context){
        tvCourse.setText(course.getCourse());
        tvSubject.setText(course.getSubject());
        System.out.println("Color:"+course.getColor());

        Drawable viewDrawable = iconCourse.getBackground();
        viewDrawable = DrawableCompat.wrap(viewDrawable);
        DrawableCompat.setTint(viewDrawable, course.getColor());
        iconCourse.setBackground(viewDrawable);

//        iconCourse.setBackgroundTintList(context.getResources().getColorStateList(course.getColor()));
//        iconCourse.setBackgroundTintList(context.getResources().getColorStateList(Color.parseColor(course.getColor()) ));
    }

}

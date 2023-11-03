package com.example.miaula.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.miaula.R
import com.example.miaula.models.Course

class CourseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var clCourse: ConstraintLayout? = null
    private var btnOptions: View? = null
    private var iconCourse: ImageView? = null
    private var tvCourse: TextView? = null
    private var tvSubject: TextView? = null

    init {
        initElements(itemView)
    }

    private fun initElements(root: View) {
        clCourse = root.findViewById(R.id.cl_student)
        iconCourse = root.findViewById(R.id.icon_course)
        btnOptions = root.findViewById(R.id.btn_remove)
        tvCourse = root.findViewById(R.id.tv_surname)
        tvSubject = root.findViewById(R.id.tv_names)
    }

    @SuppressLint("ResourceType")
    fun prepareElements(course: Course, context: Context) {
        tvCourse!!.text = course.course
        tvSubject!!.text = course.subject

//        Drawable viewDrawable = iconCourse.getBackground();
//        viewDrawable = DrawableCompat.wrap(viewDrawable);
//        DrawableCompat.setTint(viewDrawable, course.getColor());
//        iconCourse.setBackground(viewDrawable);
        iconCourse!!.backgroundTintList = context.resources.getColorStateList(course.color)

        //Options-Course
        btnOptions!!.setOnClickListener { println("click") }
    }
}
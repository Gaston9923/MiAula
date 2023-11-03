package com.example.miaula.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.miaula.ui.adapters.CoursesRecyclerAdapter
import com.example.miaula.Controllers.CourseController
import com.example.miaula.models.Course
import com.example.miaula.R

@SuppressLint("ValidFragment")
class ListCoursesFragment(private val courseController: CourseController) : Fragment() {
    private var rvListCourses: RecyclerView? = null
    private val courses: ArrayList<Course>? = null
    private var coursesRecyclerAdapter: CoursesRecyclerAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.list_courses_fragment, container, false)
        rvListCourses = root.findViewById(R.id.rv_list_courses)
//        rvListCourses.setLayoutManager(LinearLayoutManager(activity))
//        rvListCourses.setHasFixedSize(true)
//        coursesRecyclerAdapter = CoursesRecyclerAdapter(courseController)
//        rvListCourses.setAdapter(coursesRecyclerAdapter)
        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateListCourses() {
        coursesRecyclerAdapter!!.notifyDataSetChanged()
    }
}
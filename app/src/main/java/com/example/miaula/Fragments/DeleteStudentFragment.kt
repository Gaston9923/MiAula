package com.example.miaula.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miaula.ui.adapters.StudentsRecyclerAdapter
import com.example.miaula.Controllers.CourseController
import com.example.miaula.R

@SuppressLint("ValidFragment")
class DeleteStudentFragment(
    private val courseController: CourseController,
    private val idCourse: Int
) : Fragment() {
    private var clDeleteStudent: ConstraintLayout? = null
    private var rvDeleteListStudent: RecyclerView? = null
    private var tvNoStudents: TextView? = null
    private var studentsRecyclerAdapter: StudentsRecyclerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.delete_student_fragment, container, false)
        initElements(root)
        rvDeleteListStudent!!.layoutManager = LinearLayoutManager(activity)
        rvDeleteListStudent!!.setHasFixedSize(true)
        studentsRecyclerAdapter =
            StudentsRecyclerAdapter(
                idCourse,
                courseController
            )
        rvDeleteListStudent!!.adapter = studentsRecyclerAdapter
        showStudents()
        return root
    }

    private fun showStudents() {
//        if (courseController.getCourse(idCourse).students!!.size == 0) {
//            tvNoStudents!!.visibility = View.VISIBLE
//            rvDeleteListStudent!!.visibility = View.INVISIBLE
//        } else {
//            tvNoStudents!!.visibility = View.INVISIBLE
//            rvDeleteListStudent!!.visibility = View.VISIBLE
//        }
    }

    private fun initElements(root: View) {
        clDeleteStudent = root.findViewById(R.id.cl_delete_student)
        rvDeleteListStudent = root.findViewById(R.id.rv_list_delete_student)
        tvNoStudents = root.findViewById(R.id.tv_no_students)
    }
}
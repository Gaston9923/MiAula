package com.example.miaula.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.miaula.Controllers.CourseController
import com.example.miaula.R

@SuppressLint("ValidFragment")
class MenuCourseFragment(private val courseController: CourseController) : Fragment() {
    private var idCourse = 0
    private var clMenuCourse: ConstraintLayout? = null
    private var btnTakeAssistance: ConstraintLayout? = null
    private var btnEditStudents: ConstraintLayout? = null
    private var btnConsultAbsences: ConstraintLayout? = null
    private var editStudentsFragment: EditStudentsFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.menu_course_fragment, container, false)
        idCourse = requireArguments().getInt("idCourse")
        initElements(root)
        btnTakeAssistance!!.setOnClickListener { }
        btnEditStudents!!.setOnClickListener { onEditStudentFragment() }
        btnConsultAbsences!!.setOnClickListener { }
        return root
    }

    private fun initElements(root: View) {
        clMenuCourse = root.findViewById(R.id.cl_edit_students)
        btnTakeAssistance = root.findViewById(R.id.btn_add_student)
        btnEditStudents = root.findViewById(R.id.btn_delete_student)
        btnConsultAbsences = root.findViewById(R.id.btn_consult_absences)
        editStudentsFragment = EditStudentsFragment(courseController, idCourse)
    }

    private fun onEditStudentFragment() {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.add(R.id.cl_fragment, editStudentsFragment!!, "EditStudentsFragment")
            .commit()
    }
}
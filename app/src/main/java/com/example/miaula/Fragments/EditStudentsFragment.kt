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
class EditStudentsFragment(
    private val courseController: CourseController,
    private val idCourse: Int
) : Fragment() {
    private var clEditStudents: ConstraintLayout? = null
    private var btnAddStudent: ConstraintLayout? = null
    private var btnDeleteStudent: ConstraintLayout? = null
    private var addStudentFragment: AddStudentFragment? = null
    private var deleteStudentFragment: DeleteStudentFragment? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.edit_course_fragment, container, false)
        initElements(root)
        btnAddStudent!!.setOnClickListener { onAddStudentFragment() }
        btnDeleteStudent!!.setOnClickListener { onDeleteStudentFragment() }
        return root
    }

    private fun onAddStudentFragment() {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.add(R.id.cl_fragment, addStudentFragment!!, "AddStudentFragment")
            .commit()
    }

    private fun onDeleteStudentFragment() {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.add(R.id.cl_fragment, deleteStudentFragment!!, "DeleteStudentFragment")
            .commit()
    }

    private fun initElements(root: View) {
        clEditStudents = root.findViewById(R.id.cl_edit_students)
        btnAddStudent = root.findViewById(R.id.btn_add_student)
        btnDeleteStudent = root.findViewById(R.id.btn_delete_student)
        addStudentFragment = AddStudentFragment(courseController, idCourse)
        deleteStudentFragment = DeleteStudentFragment(courseController, idCourse)
    }
}
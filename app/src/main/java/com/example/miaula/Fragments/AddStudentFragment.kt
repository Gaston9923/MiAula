package com.example.miaula.Fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.miaula.Controllers.CourseController
import com.example.miaula.models.Student
import com.example.miaula.R

@SuppressLint("ValidFragment")
class AddStudentFragment(
    private val courseController: CourseController,
    private val idCourse: Int
) : Fragment() {
    private var btnAddStudent: Button? = null
    private var etSurnames: EditText? = null
    private var etNames: EditText? = null
    private var spGender: Spinner? = null
//    private var optionsGender: Array<String>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.add_student_fragment, container, false)
        initElements(root)
//        val adapter =
//            ArrayAdapter(context!!, android.R.layout.simple_spinner_dropdown_item, optionsGender)
//        spGender!!.adapter = adapter
        val idStudent = 0
        btnAddStudent!!.setOnClickListener {
            val s = Student(
                idStudent + 1, etSurnames!!.text.toString(),
                etNames!!.text.toString(),
                spGender!!.selectedItem.toString()
            )
//            courseController.addStudent(idCourse, s)
            showAlertNewStudent()
            cleanInputs()
        }
        return root
    }

    private fun showAlertNewStudent() {
        val alertBox = AlertDialog.Builder(context)
        val viewAlert =
            LayoutInflater.from(context).inflate(R.layout.alert_new_student, null, false)
        alertBox.setView(viewAlert)
        val btn_ok = viewAlert.findViewById<Button>(R.id.btn_confirm)
        val d = alertBox.show()
        btn_ok.setOnClickListener { d.dismiss() }
    }

    private fun cleanInputs() {
        etSurnames!!.setText("")
        etNames!!.setText("")
    }

    private fun initElements(root: View) {
        btnAddStudent = root.findViewById(R.id.btn_new_student)
        etSurnames = root.findViewById(R.id.et_surnames)
        etNames = root.findViewById(R.id.et_names)
        spGender = root.findViewById(R.id.sp_gender)
//        optionsGender = arrayOf("Masculino", "Femenino")
    }
}
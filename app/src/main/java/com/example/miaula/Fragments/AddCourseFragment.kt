package com.example.miaula.Fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.miaula.Controllers.CourseController
import com.example.miaula.MainActivity
import com.example.miaula.models.Course
import com.example.miaula.R

@SuppressLint("ValidFragment")
class AddCourseFragment @SuppressLint("ValidFragment") constructor(private val courseController: CourseController) :
    Fragment() {
    private val self = this
    private val listCourses = ArrayList<Course>()
    private var etSchool: EditText? = null
    private var etCourse: EditText? = null
    private var etSubject: EditText? = null
    private var tvSelectColor: TextView? = null
    private var colorCourse = 0
    private var btnPink: Button? = null
    private var btnBlue: Button? = null
    private var btnYellow: Button? = null
    private var btnOrange: Button? = null
    private var btnPurple: Button? = null
    private var btnAdd: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.add_course_fragment, container, false)
        initElements(root)
        val mainActivity = activity as MainActivity?
        btnAdd!!.setOnClickListener {
            if (inputsValidate()) {
                val alertBox = AlertDialog.Builder(context)
                val viewAlert =
                    LayoutInflater.from(context).inflate(R.layout.alert_new_course, null, false)
                alertBox.setView(viewAlert)
                val btn_ok = viewAlert.findViewById<Button>(R.id.btn_confirm)
                val d = alertBox.show()
                btn_ok.setOnClickListener { d.dismiss() }
                val id = 0
//                val course = Course(
//                    id + 1, etSchool!!.text.toString(),
//                    etCourse!!.text.toString(),
//                    etSubject!!.text.toString(), colorCourse
//                )
//                courseController.addCourse(course)
//                saveInDevice(course)
//                fragmentManager!!.beginTransaction().remove(self).commit()
//                mainActivity!!.onResumeCourses()
            }
        }
        btnPink!!.setOnClickListener { selectColor(btnPink, R.color.pink) }
        btnBlue!!.setOnClickListener { selectColor(btnBlue, R.color.blue) }
        btnYellow!!.setOnClickListener { selectColor(btnYellow, R.color.yellow) }
        btnOrange!!.setOnClickListener { selectColor(btnOrange, R.color.orange) }
        btnPurple!!.setOnClickListener { selectColor(btnPurple, R.color.purple) }
        return root
    }

    private fun saveInDevice(course: Course) {
//        val sharedPreferences = activity!!.getSharedPreferences("data", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        val gson = Gson()
//        listCourses.add(course)
//        val json = gson.toJson(listCourses)
//        editor.putString("courses", json)
//        editor.apply()
//        println("Curso guardado en el dispositivo!")
    }

    private fun inputsValidate(): Boolean {
        var isValid = true
        if (etSchool!!.text.toString() == "") {
            isValid = false
            etSchool!!.error = "Debe completar el campo!"
            return isValid
        }
        if (etCourse!!.text.toString() == "") {
            etCourse!!.error = "Debe completar el campo!"
            isValid = false
            return isValid
        }
        if (etSubject!!.text.toString() == "") {
            etSubject!!.error = "Debe completar el campo!"
            isValid = false
            return isValid
        }
        return isValid
    }

    private fun initElements(root: View) {
        etSchool = root.findViewById(R.id.et_school)
        etCourse = root.findViewById(R.id.et_course)
        etSubject = root.findViewById(R.id.et_subject)
        btnPink = root.findViewById(R.id.btn_pink)
        btnOrange = root.findViewById(R.id.btn_orange)
        btnBlue = root.findViewById(R.id.btn_blue)
        btnPurple = root.findViewById(R.id.btn_purple)
        btnYellow = root.findViewById(R.id.btn_yellow)
        btnAdd = root.findViewById(R.id.btn_add)
        tvSelectColor = root.findViewById(R.id.tv_select_color)
        colorCourse = R.color.white
    }

    @SuppressLint("ResourceAsColor")
    private fun selectColor(btnColor: Button?, color: Int) {
        btnColor!!.elevation = 6f
        println("color:$color")
        tvSelectColor!!.setTextColor(resources.getColor(color))
        colorCourse = color
    }
}
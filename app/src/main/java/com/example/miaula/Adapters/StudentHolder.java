package com.example.miaula.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miaula.Controllers.CourseController;
import com.example.miaula.Models.Student;
import com.example.miaula.R;

public class StudentHolder extends RecyclerView.ViewHolder {

    private StudentsRecyclerAdapter adapter;
    private Context context;
    private ConstraintLayout clStudent;
    private TextView tvSurname;
    private TextView tvNames;
    private ImageView iconStudentMen;
    private ImageView iconStudentWomen;
    private ImageView btnRemove;

    public StudentHolder(@NonNull View itemView, StudentsRecyclerAdapter adapter , Context context) {
        super(itemView);
        this.context = context;
        this.adapter = adapter;
        initElements(itemView);
    }

    private void initElements(View root){
        clStudent = root.findViewById(R.id.cl_delete_student);
        tvSurname = root.findViewById(R.id.tv_delete_surname);
        tvNames = root.findViewById(R.id.tv_delete_names);
        iconStudentMen = root.findViewById(R.id.icon_student_men);
        iconStudentWomen = root.findViewById(R.id.icon_student_women);
        btnRemove = root.findViewById(R.id.btn_remove);
    }

    public void prepareView(int idCourse, Student student, CourseController courseController){
        tvSurname.setText(student.getSurname());
        tvNames.setText(student.getName());
        if (student.getGender().equals("Masculino")){
            iconStudentMen.setVisibility(View.VISIBLE);
            iconStudentWomen.setVisibility(View.INVISIBLE);
        }else {
            iconStudentWomen.setVisibility(View.VISIBLE);
            iconStudentMen.setVisibility(View.INVISIBLE);
        }
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertConfirmDelete(courseController,idCourse,student);
            }
        });
    }

    private void showAlertConfirmDelete(CourseController courseController, int idCourse, Student student){
        AlertDialog.Builder alertBox = new AlertDialog.Builder(context);
        View viewAlert = LayoutInflater.from(context).inflate(R.layout.alert_confirm_delete_student, null, false);
        alertBox.setView(viewAlert);
        Button btnConfirm = viewAlert.findViewById(R.id.btn_confirm);
        Button btnCancel = viewAlert.findViewById(R.id.btn_cancel);
        final AlertDialog d = alertBox.show();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseController.getCourses().get(idCourse).getStudents().remove(student.getIdCourse());
                adapter.notifyDataSetChanged();
                d.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });
    }

}

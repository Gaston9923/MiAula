package com.example.miaula;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


import com.example.miaula.Controllers.CourseController;
import com.example.miaula.Fragments.AddCourseFragment;
import com.example.miaula.Fragments.ListCoursesFragment;
import com.example.miaula.Models.Course;
import com.example.miaula.Models.User;
import com.google.android.material.circularreveal.CircularRevealFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private final MainActivity self = this;
    private CourseController courseController = CourseController.getInstance();
    private ListCoursesFragment listCoursesFragment;
    private AddCourseFragment addCourseFragment;
    private ArrayList<Course> arrayCourses;
    private User userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserLogged();

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        arrayCourses = new ArrayList<>();
        getCoursesFromDevice();
        addListCourses();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
//                getSupportFragmentManager().popBackStack();
                if (getSupportFragmentManager().getFragments().size() == 1){
                    System.out.println("un solo fragmento");
                    fab.setVisibility(View.VISIBLE);
                    return;
                }else fab.setVisibility(View.INVISIBLE);
                Fragment fragment = getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size()-1);
                System.out.println("Fragmento:" +fragment.getTag());
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                if (getSupportFragmentManager().getFragments().size() == 2){
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    fab.setVisibility(View.VISIBLE);
                    return;
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCourseFragment();
            }
        });
    }

    private void getUserLogged(){
        userLogin = (User) getIntent().getSerializableExtra("userLogin");
        System.out.println("user: "+userLogin);
//        Toast.makeText(getApplicationContext(), (CharSequence) userLogin,Toast.LENGTH_LONG).show();
    }

    private void getCoursesFromDevice(){
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("courses",null);
        if (json != null){
            Type type = new TypeToken<ArrayList<Course>>() {}.getType();
            arrayCourses = gson.fromJson(json,type);
            System.out.println("Cursos:"+arrayCourses);
            courseController.setCourses(arrayCourses);
        }
        courseController.setCourses(new ArrayList<>());

    }

    @SuppressLint("RestrictedApi")
    private void addCourseFragment(){
        addCourseFragment = new AddCourseFragment(courseController);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.cl_fragment, addCourseFragment, "AddCourseFragment").commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fab.setVisibility(View.GONE);
    }

    private void addListCourses(){
        listCoursesFragment = new ListCoursesFragment(courseController);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.cl_fragment, listCoursesFragment, "ListCoursesFragment").commit();
    }

    @SuppressLint("RestrictedApi")
    public void hideFab(){
        fab.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("RestrictedApi")
    public void onResumeCourses(){
        listCoursesFragment.updateListCourses();
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().size() == 1){
            System.out.println("un solo fragmento");
            fab.setVisibility(View.VISIBLE);
            return;
        }else fab.setVisibility(View.INVISIBLE);
        Fragment fragment = getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size()-1);
        System.out.println("Fragmento:" +fragment.getTag());
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        if (getSupportFragmentManager().getFragments().size() == 2){
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            fab.setVisibility(View.VISIBLE);
            return;
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onResume() {
        if (getSupportFragmentManager().getFragments().size() == 1){
            fab.setVisibility(View.VISIBLE);
        }else fab.setVisibility(View.INVISIBLE);
        super.onResume();
    }
}
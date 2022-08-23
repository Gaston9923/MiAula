package com.example.miaula;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.miaula.Controllers.CourseController;
import com.example.miaula.Fragments.AddCourseFragment;
import com.example.miaula.Fragments.ListCoursesFragment;
import com.example.miaula.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;

    private CourseController courseController;
    private ListCoursesFragment listCoursesFragment;
    private AddCourseFragment addCourseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        courseController = new CourseController();
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
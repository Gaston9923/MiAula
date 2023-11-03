package com.example.miaula

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.miaula.Controllers.CourseController
import com.example.miaula.Fragments.AddCourseFragment
import com.example.miaula.Fragments.ListCoursesFragment
import com.example.miaula.models.Course
import com.example.miaula.models.User
import com.example.miaula.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val fab: FloatingActionButton? = null
    private val self = this
    private val courseController = CourseController.instance
    private var listCoursesFragment: ListCoursesFragment? = null
    private var addCourseFragment: AddCourseFragment? = null
    private var arrayCourses: ArrayList<Course>? = null
    private var userLogin: User? = null
    private val db = FirebaseFirestore.getInstance()


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)
        userLogged
        dataFromDB

//        setSupportActionBar(toolbar);
        arrayCourses = ArrayList()
        coursesFromDevice
        addListCourses()
        binding!!.mainToolbar.title.text = "Mi Aula"
        binding!!.mainToolbar.btnClose.visibility = View.GONE
        binding!!.mainToolbar.btnBack.setOnClickListener(View.OnClickListener { //                getSupportFragmentManager().popBackStack();
            if (supportFragmentManager.fragments.size == 1) {
                println("un solo fragmento")
                fab!!.visibility = View.VISIBLE
                return@OnClickListener
            } else fab!!.visibility = View.INVISIBLE
            val fragment =
                supportFragmentManager.fragments[supportFragmentManager.fragments.size - 1]
            println("Fragmento:" + fragment.tag)
            supportFragmentManager.beginTransaction().remove(fragment).commit()
            if (supportFragmentManager.fragments.size == 2) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                fab.visibility = View.VISIBLE
                return@OnClickListener
            }
        })

//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @SuppressLint("RestrictedApi")
//            @Override
//            public void onClick(View view) {
////                getSupportFragmentManager().popBackStack();
//                if (getSupportFragmentManager().getFragments().size() == 1){
//                    System.out.println("un solo fragmento");
//                    fab.setVisibility(View.VISIBLE);
//                    return;
//                }else fab.setVisibility(View.INVISIBLE);
//                Fragment fragment = getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size()-1);
//                System.out.println("Fragmento:" +fragment.getTag());
//                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
//                if (getSupportFragmentManager().getFragments().size() == 2){
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                    fab.setVisibility(View.VISIBLE);
//                    return;
//                }
//            }
//        });
        binding!!.fab.setOnClickListener { addCourseFragment() }
    }

    private val dataFromDB: Unit
        private get() {
            val users = db.collection("Users")
            println("Usuarios: " + users.get().toString())
        }

    //        Toast.makeText(getApplicationContext(), (CharSequence) userLogin,Toast.LENGTH_LONG).show();
    private val userLogged: Unit
        private get() {
            userLogin = intent.getSerializableExtra("userLogin") as User?
            println("user: $userLogin")
            //        Toast.makeText(getApplicationContext(), (CharSequence) userLogin,Toast.LENGTH_LONG).show();
        }
    private val coursesFromDevice: Unit
        private get() {
            val sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
            val gson = Gson()
            val json = sharedPreferences.getString("courses", null)
            if (json != null) {
                val type = object : TypeToken<ArrayList<Course?>?>() {}.type
                arrayCourses = gson.fromJson(json, type)
                println("Cursos:$arrayCourses")
                courseController?.courses = arrayCourses
            }
            courseController?.courses = ArrayList()
        }

    @SuppressLint("RestrictedApi")
    private fun addCourseFragment() {
//        addCourseFragment = AddCourseFragment(courseController)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.add(R.id.cl_fragment, addCourseFragment!!, "AddCourseFragment").commit()
        //        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding!!.fab.visibility = View.GONE
    }

    private fun addListCourses() {
//        listCoursesFragment = ListCoursesFragment(courseController)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.add(R.id.cl_fragment, listCoursesFragment!!, "ListCoursesFragment")
            .commit()
    }

    @SuppressLint("RestrictedApi")
    fun hideFab() {
        binding!!.fab.visibility = View.INVISIBLE
    }

    @SuppressLint("RestrictedApi")
    fun onResumeCourses() {
        listCoursesFragment!!.updateListCourses()
        binding!!.fab.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    @SuppressLint("RestrictedApi")
    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size == 1) {
            println("un solo fragmento")
            binding!!.fab.visibility = View.VISIBLE
            return
        } else binding!!.fab.visibility = View.INVISIBLE
        val fragment = supportFragmentManager.fragments[supportFragmentManager.fragments.size - 1]
        println("Fragmento:" + fragment.tag)
        supportFragmentManager.beginTransaction().remove(fragment).commit()
        if (supportFragmentManager.fragments.size == 2) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            binding!!.fab.visibility = View.VISIBLE
            return
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onResume() {
        if (supportFragmentManager.fragments.size == 1) {
            binding!!.fab.visibility = View.VISIBLE
        } else binding!!.fab.visibility = View.INVISIBLE
        super.onResume()
    }
}
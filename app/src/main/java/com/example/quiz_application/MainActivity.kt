package com.example.quiz_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var dbref:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

        var teacher=findViewById<Button>(R.id.tbtn)
        teacher.setOnClickListener {
            var intent=Intent(applicationContext,teacherMain::class.java)
            startActivity(intent)
        }

        var student=findViewById<Button>(R.id.sbtn)
        student.setOnClickListener {
            var intent=Intent(applicationContext,studentmain::class.java)
            startActivity(intent)
        }
    }
}
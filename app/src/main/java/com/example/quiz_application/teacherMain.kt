package com.example.quiz_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class teacherMain : AppCompatActivity() {
    lateinit var id:EditText
    lateinit var pwd:EditText
    lateinit var btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_teacher_main)

        id=findViewById<EditText>(R.id.idtxt)
        pwd=findViewById<EditText>(R.id.passtxt)
        btn=findViewById(R.id.continuebtn)
        btn.setOnClickListener {
            if (id.text.toString()=="" || pwd.text.toString()=="") {
                if (id.text.toString() == "") {
                    id.error = "Enter unique id"
                }
                if (pwd.text.toString() == "") {
                    pwd.error = "Enter password"

                }
            }
            else{
                if (id.text.toString()=="janvi" || id.text.toString()=="janvi") {

                    if (pwd.text.toString() == "janvi") {
                        var intent= Intent(applicationContext,teachQuestion::class.java)
                        startActivity(intent)
                    } else {
                        pwd.error = "Password is invalid"
                        Toast.makeText(
                            applicationContext,
                            "password is incorrect",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                else{
                    id.error = "id is invalid"
                    Toast.makeText(
                        applicationContext,
                        "id is incorrect",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }
        }

    }
}
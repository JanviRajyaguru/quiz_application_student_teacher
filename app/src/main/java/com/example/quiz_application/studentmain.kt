package com.example.quiz_application

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class studentmain : AppCompatActivity() {
    lateinit var nametxt: EditText
    lateinit var roltxt: EditText
    lateinit var mobtxt: EditText
    lateinit var usercap: EditText
    lateinit var btn: Button
    lateinit var captxt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_studentmain)
        nametxt = findViewById(R.id.nmtxt)
        roltxt = findViewById(R.id.rnum)
        mobtxt = findViewById(R.id.pnum)
        usercap = findViewById(R.id.cap)
        captxt = findViewById(R.id.captxt)
        btn = findViewById(R.id.nxtbtn)
        val cc = (1..5).map { ('a'..'z').random() }.joinToString("")
        captxt.text = cc

        btn.setOnClickListener {
            if (nametxt.text.toString() == "" || roltxt.text.toString() == "" || mobtxt.text.toString() == ""
                || usercap.text.toString() == "") {

                if (nametxt.text.toString() == "") {
                    nametxt.error = "This field is required"
                }
                if (roltxt.text.toString() == "") {
                    roltxt.error = "This field is required"
                }
                if (mobtxt.text.toString() == "") {
                    mobtxt.error = "This field is required"
                }
                if (usercap.text.toString() == "") {
                    usercap.error = "This field is required"
                }
            }else{
                if (usercap.text.toString()!=captxt.text.toString()){
                    usercap.error="Captcha does not match..."
                }
                else{
                    val sharedPref = getSharedPreferences("name", Context.MODE_PRIVATE)

                    val editor = sharedPref.edit()

                    editor.putString("n",nametxt.text.toString())

                    editor.apply()
                    editor.commit()
                    var i =Intent(applicationContext,test::class.java)
                    startActivity(i)
                }
            }
        }
    }
}

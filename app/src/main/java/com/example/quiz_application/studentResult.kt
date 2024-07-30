package com.example.quiz_application

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.RelativeLayout
import android.widget.TextView

class studentResult : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_student_result)
        val sharedPref = getSharedPreferences("name", Context.MODE_PRIVATE)

        var nn=sharedPref.getString("n","a")

        var mark=intent.getStringExtra("result")
        var que=intent.getStringExtra("que")


        var lay=findViewById<RelativeLayout>(R.id.retivlay)
        var ttview=findViewById<TextView>(R.id.tttview)
        var aa= ScaleAnimation(1.0f,1.5f,1.0f,1.5f,
            Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        aa.repeatCount=2
        aa.repeatMode=2
        aa.duration=1000
        lay.startAnimation(aa)
        ttview.text="Hiii "+nn+" !\nYou got "+mark+" mark out of "+ que +" in this exam... "


    }
}
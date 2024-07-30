package com.example.quiz_application

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog

class testgive : AppCompatActivity() {
    lateinit var que: TextView
    lateinit var o1: TextView
    lateinit var o2: TextView
    lateinit var o3: TextView
    lateinit var o4: TextView
    lateinit var btn: Button
    lateinit var rbtn1: RadioButton
    lateinit var rbtn2: RadioButton
    lateinit var rbtn3: RadioButton
    lateinit var rbtn4: RadioButton
    lateinit var result: Button
    lateinit var realans: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_testgive)
        var finalresult = 0
        realans = emptyArray()
        var i: Int = 1

        //geting all array
        val question = intent.getStringArrayExtra("que")
        val opt1 = intent.getStringArrayExtra("o1")
        val opt2 = intent.getStringArrayExtra("o2")
        val opt3 = intent.getStringArrayExtra("o3")
        val opt4 = intent.getStringArrayExtra("o4")
        val teacherans = intent.getStringArrayExtra("ans")

        //fwbi fro textview btn
        que = findViewById(R.id.questiontxt)
        o1 = findViewById(R.id.opt1)
        o2 = findViewById(R.id.opt2)
        o3 = findViewById(R.id.opt3)
        o4 = findViewById(R.id.opt4)

        var greeting = findViewById<TextView>(R.id.gretg)
        val sharedPref = getSharedPreferences("name", Context.MODE_PRIVATE)

        var nn = sharedPref.getString("n", "a")
        greeting.text = "Hello !!  " + nn+"\n● All the question are important " +
                "\n● Each question take 1 mark.\n● You have to select one option from 4 ✍️ " +
                "\n All the Best 😊😊"

        rbtn1 = findViewById(R.id.rbtn1)
        rbtn2 = findViewById(R.id.rbtn2)
        rbtn3 = findViewById(R.id.rbtn3)
        rbtn4 = findViewById(R.id.rbtn4)

        result = findViewById(R.id.seresbtn)
        btn = findViewById(R.id.btnn)


        result.visibility = View.INVISIBLE


        que.text = question!![0].toString()
        o1.text = opt1!![0].toString()
        o2.text = opt2!![0].toString()
        o3.text = opt3!![0].toString()
        o4.text = opt4!![0].toString()
        var n = question!!.size
        btn.setOnClickListener {
            if (rbtn1.isChecked == false && rbtn2.isChecked == false && rbtn3.isChecked == false && rbtn4.isChecked == false) {
                Toast.makeText(applicationContext, "Select one option ", Toast.LENGTH_SHORT).show()
            } else {

                if (i >= n) {
                    result.visibility = View.VISIBLE
                    btn.visibility = View.INVISIBLE
                } else {


                    que.text = question!![i].toString()
                    o1.text = opt1!![i].toString()
                    o2.text = opt2!![i].toString()
                    o3.text = opt3!![i].toString()
                    o4.text = opt4!![i].toString()

                    if (rbtn1.isChecked == true) {
                        realans = realans.plus("A")
                    }
                    if (rbtn2.isChecked == true) {
                        realans = realans.plus("B")
                    }
                    if (rbtn3.isChecked == true) {
                        realans = realans.plus("C")
                    }
                    if (rbtn4.isChecked == true) {
                        realans = realans.plus("D")
                    }
                    rbtn1.setChecked(false)
                    rbtn2.setChecked(false)
                    rbtn3.setChecked(false)
                    rbtn4.setChecked(false)

                    i = i + 1
                }
            }
        }
        result.setOnClickListener {

            if (rbtn1.isChecked == true) {
                realans = realans.plus("A")
            }
            if (rbtn2.isChecked == true) {
                realans = realans.plus("B")
            }
            if (rbtn3.isChecked == true) {
                realans = realans.plus("C")
            }
            if (rbtn4.isChecked == true) {
                realans = realans.plus("D")
            }

            for (i in realans.indices){
                if (realans[i].toString()== teacherans!![i].toString()){
                    Log.d("gdf",realans.size.toString())
                    Log.d("gdfd",teacherans.size.toString())

                    Log.d("dsgdrg",realans[i].toString())
                    finalresult=finalresult+1
                }

            }
            var totque=realans.size
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Your quiz is submitted press ok to see result...")
            builder.setPositiveButton("OK") { dialog, which ->

                var i=Intent(applicationContext,studentResult::class.java)
                i.putExtra("que",totque.toString())
                i.putExtra("result",finalresult.toString())
                startActivity(i)
            }
            val alertDialog = builder.create()
            alertDialog.show()




        }


    }
}

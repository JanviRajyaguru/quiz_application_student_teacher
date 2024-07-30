package com.example.quiz_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class teachQuestion : AppCompatActivity() {
    lateinit var dbref: DatabaseReference
    lateinit var que: EditText
    lateinit var opt1: EditText
    lateinit var opt2: EditText
    lateinit var opt3: EditText
    lateinit var opt4: EditText
    lateinit var rbtn1:RadioButton
    lateinit var rbtn2:RadioButton
    lateinit var rbtn3:RadioButton
    lateinit var rbtn4:RadioButton
    lateinit var nxque: Button
    lateinit var submit: Button
    lateinit var ar: Array<String>
    lateinit var ar1: Array<String>
    lateinit var ar2: Array<String>
    lateinit var ar3: Array<String>
    lateinit var ar4: Array<String>
    lateinit var ans: Array<String>

    lateinit var answer:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_teach_question)

        dbref = FirebaseDatabase.getInstance().getReference("Que")
        que = findViewById(R.id.question)
        opt1 = findViewById(R.id.opt1)
        opt2 = findViewById(R.id.opt2)
        opt3 = findViewById(R.id.opt3)
        opt4 = findViewById(R.id.opt4)

        rbtn1=findViewById(R.id.rbtn1)
        rbtn2=findViewById(R.id.rbtn2)
        rbtn3=findViewById(R.id.rbtn3)
        rbtn4=findViewById(R.id.rbtn4)
        nxque = findViewById(R.id.nxtque)
        submit = findViewById(R.id.submit)

        var ar: Array<String> = emptyArray()
        var ar1: Array<String> = emptyArray()
        var ar2: Array<String> = emptyArray()
        var ar3: Array<String> = emptyArray()
        var ar4: Array<String> = emptyArray()
        var ans: Array<String> = emptyArray()


        nxque.setOnClickListener {
            if (rbtn1.isChecked==true||rbtn2.isChecked==true||rbtn3.isChecked==true||rbtn4.isChecked==true){

            }
            else{
                Toast.makeText(applicationContext,"Plese select right answer",Toast.LENGTH_SHORT).show()

            }
            if (que.text.toString() == "" || opt1.text.toString() == "" ||
                opt2.text.toString() == "" || opt3.text.toString() == "" || opt4.text.toString() == ""
            ) {
                if (que.text.toString() == "") {
                    que.error = "Enter Question first"
                }
                if (opt1.text.toString() == "") {
                    opt1.error = "Add Option 1"
                }
                if (opt2.text.toString() == "") {
                    opt2.error = "Add Option 2"
                }
                if (opt3.text.toString() == "") {
                    opt3.error = "Add Option 3"
                }
                if (opt4.text.toString() == "") {
                    opt4.error = "Add Option 4"
                }
            } else {
                if (rbtn1.isChecked==true){
                    answer="A"
                }
                if (rbtn2.isChecked==true){
                    answer="B"
                }
                if (rbtn3.isChecked==true){
                    answer="C"
                }
                if (rbtn4.isChecked==true){
                    answer="D"
                }
                ar = ar.plus(que.text.toString())
                ar1 = ar1.plus(opt1.text.toString())
                ar2 = ar2.plus(opt2.text.toString())
                ar3 = ar3.plus(opt3.text.toString())
                ar4 = ar4.plus(opt4.text.toString())
                ans=ans.plus(answer)
                que.text.clear()
                opt1.text.clear()
                opt2.text.clear()
                opt3.text.clear()
                opt4.text.clear()

                val builder = AlertDialog.Builder(this)
                builder.setMessage("Question Added")
                builder.setPositiveButton("OK", null)
                val alertDialog = builder.create()
                alertDialog.show()

                rbtn1.setChecked(false)
                rbtn2.setChecked(false)
                rbtn3.setChecked(false)
                rbtn4.setChecked(false)

                // Toast.makeText(applicationContext, ar.joinToString(), Toast.LENGTH_SHORT).show()
                //Toast.makeText(applicationContext, ar3.joinToString(), Toast.LENGTH_SHORT).show()
            }
        }


        submit.setOnClickListener {

            for (i in ar.indices) {

                val qid = dbref.push().key!!
                val question = ar[i]
                val option1 = ar1[i]
                val option2 = ar2[i]
                val option3 = ar3[i]
                val option4 = ar4[i]

                val ann=ans[i]
                val mod = datamodel(qid, question, option1, option2, option3, option4,ann)
                dbref.child(qid).setValue(mod)
                rbtn1.setChecked(false)
                rbtn2.setChecked(false)
                rbtn3.setChecked(false)
                rbtn4.setChecked(false)

                val builder = AlertDialog.Builder(this)
                builder.setMessage("Question paper is generated 👍👍👍🫡")
                builder.setPositiveButton("OK") { dialog, which ->
                    var intent=Intent(applicationContext,teacherMain::class.java)
                    startActivity(intent)
                }
                val alertDialog = builder.create()
                alertDialog.show()


            }
        }
    }
}
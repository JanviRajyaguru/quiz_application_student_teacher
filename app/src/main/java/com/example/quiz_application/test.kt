package com.example.quiz_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.google.firebase.database.*

class test : AppCompatActivity() {
    lateinit var dbRef:DatabaseReference
    lateinit var questionlist:Array<String>
    lateinit var opt1list:Array<String>
    lateinit var opt2list:Array<String>
    lateinit var opt3list:Array<String>
    lateinit var opt4list:Array<String>
    lateinit var relans:Array<String>
    lateinit var studentans:Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_test)

        dbRef = FirebaseDatabase.getInstance().getReference("Que")

        questionlist= emptyArray()
        opt1list= emptyArray()
        opt2list= emptyArray()
        opt3list= emptyArray()
        opt4list= emptyArray()
        relans= emptyArray()
        studentans= emptyArray()
        //geting all data in to array ok...
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (questionSnapshot in snapshot.children) {
                    val question = questionSnapshot.child("que").value.toString()
                    val o1=questionSnapshot.child("opt1").value.toString()
                    val o2=questionSnapshot.child("opt2").value.toString()
                    val o3=questionSnapshot.child("opt3").value.toString()
                    val o4=questionSnapshot.child("opt4").value.toString()
                    val an=questionSnapshot.child("ans").value.toString()
                    questionlist= questionlist.plus(question)
                    opt1list= opt1list.plus(o1)
                   opt2list= opt2list.plus(o2)
                   opt3list= opt3list.plus(o3)
                  opt4list=  opt4list.plus(o4)
                    relans=  relans.plus(an)

                    joaway()

                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun joaway() {
        var i=Intent(applicationContext, testgive::class.java)
        i.putExtra("que",questionlist)
        i.putExtra("o1",opt1list)
        i.putExtra("o2",opt2list)
        i.putExtra("o3",opt3list)
        i.putExtra("o4",opt4list)
        i.putExtra("ans",relans)
        startActivity(i)
    }
}

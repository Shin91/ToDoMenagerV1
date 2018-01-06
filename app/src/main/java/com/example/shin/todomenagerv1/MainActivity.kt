package com.example.shin.todomenagerv1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    var task: Tasks = Tasks()
    lateinit var card : CardView

    lateinit var btnAddTask: Button
    lateinit var tasks: ArrayList<Tasks>
    private lateinit var edtAddTask: EditText
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db = DatabaseHelper(this)
        tasks = ArrayList()

        btnAddTask = findViewById(R.id.btnAddTask)
        edtAddTask = find(R.id.edtAddTask)


        recyclerView = find(R.id.recView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = MyAdapter(this, tasks, recyclerView)


        if (db.getTaskCount() > 0) {
            tasks = db.getAllTasks()
            recyclerView.adapter = MyAdapter(this, tasks, recyclerView)
        }

/*
        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as MyAdapter

                adapter.deleteRowAndTask(v)
            }
        }*/


        btnAddTask.setOnClickListener {
            addTaskToView(db)
        }



    }









    fun addTaskToView(db: DatabaseHelper) {


        val t1 = edtAddTask.text.toString()
        if (t1.equals("")) {
            Toast.makeText(this, "Enter new task first ", Toast.LENGTH_SHORT).show()
        } else {

            task.task = t1
            db.addTask(task)

            tasks = db.getAllTasks()

            recyclerView.adapter = MyAdapter(this, tasks, recyclerView)

            edtAddTask.setText("")
        }


    }





}









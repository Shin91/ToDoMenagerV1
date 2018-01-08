package com.example.shin.todomenagerv1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.jetbrains.anko.find

class AddTaskActivity : AppCompatActivity() {

    var task : Tasks = Tasks()



    lateinit var btnConfirm : Button
    lateinit var edtAddTask : EditText
    lateinit var edtAddDesc : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        btnConfirm = find(R.id.btnConfirm)
        edtAddTask = find(R.id.edtTaskTitle)
        edtAddDesc = find(R.id.edtTaskDesc)

        val db = DatabaseHelper(this)


        btnConfirm.setOnClickListener {
            val intent : Intent = Intent(this, MainActivity::class.java)
            addTaskToDataBase(db)
            startActivity(intent)

        }

    }









    fun addTaskToDataBase(db: DatabaseHelper) {

        val t1 = edtAddTask.text.toString()
        val t2 = edtAddDesc.text.toString()
        if (t1.equals("")) {
            Toast.makeText(this, "Enter new task first ", Toast.LENGTH_SHORT).show()
        } else {
            if (t2.equals("")) {
                task.task = t1
                db.addTask(task)
            }else{
                task.task = t1
                task.description = t2
                db.addTask(task)

                edtAddTask.setText("")
                edtAddDesc.setText("")
            }

            edtAddTask.setText("")
            edtAddDesc.setText("")
        }

    }
}

package com.example.shin.todomenagerv1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Button
import android.widget.ImageButton
import org.jetbrains.anko.find
import java.util.*


class MainActivity : AppCompatActivity() {

    private var task: Tasks = Tasks()

    private lateinit var imgButton: ImageButton
    private lateinit var btnAddTask: Button
    private lateinit var tasks: ArrayList<Tasks>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = DatabaseHelper(this)
        tasks = ArrayList()

        btnAddTask = find(R.id.btnAddTask)
        imgButton = find(R.id.imageButton)

        recyclerView = find(R.id.recView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = MyAdapter(this, tasks, recyclerView)

        setRecyclerViewItemTouchListener()
        addTaskToView(db)

        btnAddTask.setOnClickListener {

            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onResume() {

        super.onResume()
        val db = DatabaseHelper(this)
        addTaskToView(db)

    }


    fun addTaskToView(db: DatabaseHelper) {

        if (db.getTaskCount() > 0) {
            tasks = db.getAllTasks()
            recyclerView.adapter = MyAdapter(this, tasks, recyclerView)

        }
    }

    fun setRecyclerViewItemTouchListener() {

        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {

                val position = viewHolder?.adapterPosition
                val adapter = MyAdapter(this@MainActivity, tasks, recyclerView)
                adapter.deleteRowAndTask(viewHolder?.itemView)


                recyclerView.adapter.notifyItemRemoved(position!!)
                recyclerView.adapter.notifyDataSetChanged()

            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)


    }

}









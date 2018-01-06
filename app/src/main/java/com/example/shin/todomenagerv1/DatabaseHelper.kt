package com.example.shin.todomenagerv1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Shin on 2017-12-30.
 */

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "taskData.db", null, 1) {

    companion object {
        val TABLE_TASK: String = "TASK_TABLE"
        val ID: String = "ID_"
        val TASK: String = "TASK"
        val DESC: String = "DESC"
    }

    val TASK_DATABASE_CREATE =
            "CREATE TABLE if not exists " + TABLE_TASK + "(" +
                    "$ID INTEGER PRIMARY KEY , " +
                    "$TASK TEXT ," +
                    "$DESC TEXT" +
                    ")"


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TASK_DATABASE_CREATE)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXIST" + TABLE_TASK)

        onCreate(db)

    }


    fun addTask(task: Tasks) {

        val db = this.writableDatabase

        var values = ContentValues()

        values.put(TASK, task.task)
        //values.put(DESC, task.description)


        db.insert(TABLE_TASK, null, values)
        db.close()

    }


    //get Single task
    fun getTask(id: Int): Tasks {

        val db = this.readableDatabase
        var task: Tasks = Tasks()

        //define a projection that specifies which columns from the database you will actually use
        //after this query
        val projection = arrayOf(ID, TASK)

        //filter results WHERE "column_name" 'value'
        //here selection is column_name and selectionArgs is value

        val selection = ID + "=?"
        val selectionArgs = arrayOf(id.toString())

        //the order in which your result needs to be returned
        val sortOrder: String? = null //pass null if you don't want it to be sorted

        //if passed 5, only 5 will be returned
        val limit: String? = null // pass null if you don't want it to limit

        val cursor = db.query(
                TABLE_TASK,       //Table to query
                projection,         //the columns to return
                selection,          //the columns to WHERE clause
                selectionArgs,      //the values for WHERE clause
                null,       //don't group the rows
                null,        // don't filter
                sortOrder,          //the sort Order
                limit               //don't limit
        )

        if (cursor != null && cursor.moveToFirst()) {
            cursor.let {
                cursor.moveToFirst()
                task = Tasks(
                        cursor.getString(0).toInt(),
                        cursor.getString(1),
                        cursor.getString(2)

                )
            }

        }
        return task
    }

    fun getAllTasks(): ArrayList<Tasks> {

        val db = this.readableDatabase

        //SQL query for getting all records from the database
        val selectQuery = "SELECT * FROM " + TABLE_TASK

        val cursor = db.rawQuery(selectQuery, null)

        cursor.let {
            if (cursor.moveToFirst()) {

                var taskList = arrayListOf<Tasks>()
                do {
                    var task = Tasks(
                            cursor.getString(0).toInt(),
                            cursor.getString(1),
                            cursor.getString(2)
                    )
                    taskList.add(task)
                } while (cursor.moveToNext())

                cursor.close()
                db.close()

                return taskList
            }
        }
        return getAllTasks()
    }


    //updating single task
    fun uptadeTask(task: Tasks): Int {

        val db = this.writableDatabase

        //new values
        val values = ContentValues()
        values.put(TASK, task.task)


        //updating row
        return db.update(TABLE_TASK, values, TASK + " =?", arrayOf(task.task))

    }

    //Deleting single task
    fun deleteTask(task: Tasks){

        val db = this.writableDatabase
        db.delete(

                TABLE_TASK,    //table name
                TASK + " =?",   //selection
                arrayOf(task.task)          // selectionArgs
                )
        db.close()
    }



    //getting task count
    fun getTaskCount(): Int {
        val db = this.writableDatabase

        val countQuery = "SELECT * FROM " + TABLE_TASK

        val cursor = db.rawQuery(countQuery, null)
        val count = cursor.count

        cursor.close()
        db.close()

        return count
    }




}

































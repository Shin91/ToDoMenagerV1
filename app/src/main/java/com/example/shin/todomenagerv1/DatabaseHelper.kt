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
        val IS_DONE: String = "IS_DONE"
    }

    val TASK_DATABASE_CREATE =
            "CREATE TABLE if not exists " + TABLE_TASK + "(" +
                    "$ID INTEGER PRIMARY KEY , " +
                    "$TASK TEXT ," +
                    "$DESC TEXT ," +
                    "$IS_DONE TEXT" +
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
        values.put(DESC, task.description)
        values.put(IS_DONE, 0)


        db.insert(TABLE_TASK, null, values)
        db.close()

    }
    
    fun getTask(id: Int): Tasks {

        val db = this.readableDatabase
        var task: Tasks = Tasks()

        val projection = arrayOf(ID, TASK)

        val selection = ID + "=?"
        val selectionArgs = arrayOf(id.toString())

        val sortOrder: String? = null

        val limit: String? = null
        val cursor = db.query(
                TABLE_TASK,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder,
                limit
        )
        if (cursor != null && cursor.moveToFirst()) {
            cursor.let {
                cursor.moveToFirst()
                task = Tasks(
                        cursor.getString(0).toInt(),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3).toInt()
                )
            }
        }
        cursor.close()
        return task
    }

    fun getAllTasks(): ArrayList<Tasks> {

        val db = this.readableDatabase

        val selectQuery = "SELECT * FROM " + TABLE_TASK

        val cursor = db.rawQuery(selectQuery, null)

        cursor.let {
            if (cursor.moveToFirst()) {

                var taskList = arrayListOf<Tasks>()
                do {
                    var task = Tasks(
                            cursor.getString(0).toInt(),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3).toInt()
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


    fun updadeTask(task: Tasks): Int {

        val db = this.writableDatabase

        val values = ContentValues()
        values.put(TASK, task.task)
        values.put(IS_DONE, task.done)

        return db.update(TABLE_TASK, values, TASK + " =?", arrayOf(task.task))

    }

    fun deleteTask(task: Tasks) {

        val db = this.writableDatabase
        db.delete(

                TABLE_TASK,
                TASK + " =?",
                arrayOf(task.task)
        )
        db.close()
    }

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

































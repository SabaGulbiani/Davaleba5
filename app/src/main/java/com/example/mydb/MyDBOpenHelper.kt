package com.example.mydb

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

abstract class MyDBOpenHelper(
    context: Context?,
    // name: String?,
    factory: SQLiteDatabase.CursorFactory?
    // version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        private val version = 1
        private val name = "MyName.db"
        val TABLE_NAME = "ACTIVITIES"
        val COLUMN_ID = "_id"
        val COLUMN_NAME1 = "RunDistance"
        val COLUMN_NAME2 = "SwimDistance"
        val COLUMN_NAME3 = "GottenCalories"

        fun onCreate(db: SQLiteDatabase?) {
            val CTEATE_ACTIVITY_TABLE(
            "CREATE TABLE$TABLE_NAME" + "("
            +COLUMN_ID + "INTEGET PRIMARY KEY,"
            +COLUMN_NAME1 + "text"
            +COLUMN_NAME2 + "text"
            +COLUMN_NAME3 + "text" + ")")

            db?.execSQL(CTEATE_ACTIVITY_TABLE)


        }


    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)
    }

    fun addName(name: Activity) {
        val values: ContentValues()
        values.put(COLUMN_NAME1, name.RunDistance)
        values.put(COLUMN_NAME2, name.SwimDistance)
        values.put(COLUMN_NAME3, name.GottenCalories)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllName(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
}
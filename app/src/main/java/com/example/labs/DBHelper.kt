package com.example.labs

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper : SQLiteOpenHelper {

    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : super(context, name, factory, version) { }

    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory, version: Int, errorHandler: DatabaseErrorHandler) : super(context, name, factory, version, errorHandler) { }

    constructor(context: Context?, name: String?, version: Int, openParams: SQLiteDatabase.OpenParams) : super(context, name, version, openParams) { }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table City ("
                +"id integer primary key autoincrement,"
                +"Name text,"
                +"Country text,"
                +"Population integer,"
                +"Language text,"
                +"Square integer"
                +");")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}


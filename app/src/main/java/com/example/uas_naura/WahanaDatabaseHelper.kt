package com.example.uas_naura

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class WahanaDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "wahana.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_WAHANA = "wahana"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_CATEGORY = "category"
        private const val COLUMN_IMAGERESID = "image"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE $TABLE_WAHANA ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_NAME TEXT,"
                + "$COLUMN_DESCRIPTION TEXT,"
                + "$COLUMN_CATEGORY TEXT,"
                + "$COLUMN_IMAGERESID INTEGER)")

        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_WAHANA")
        onCreate(db)
    }

    fun addWahana(name: String, description: String, category: String, imageResID: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_DESCRIPTION, description)
            put(COLUMN_CATEGORY, category)
            put(COLUMN_IMAGERESID, imageResID)
        }
        db.insert(TABLE_WAHANA, null, values)
        db.close()
    }

    fun getAllWahana(): List<Wahana> {
        val wahanaList = mutableListOf<Wahana>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_WAHANA", null)
        if (cursor.moveToFirst()) {
            do {
                val wahana = Wahana(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGERESID))
                )
                wahanaList.add(wahana)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return wahanaList
    }

    fun updateWahana(id: Int, name: String, description: String, category: String, imageResID: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_DESCRIPTION, description)
            put(COLUMN_CATEGORY, category)
            put(COLUMN_IMAGERESID, imageResID)
        }
        db.update(TABLE_WAHANA, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
    }

    fun deleteWahana(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_WAHANA, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
    }
}

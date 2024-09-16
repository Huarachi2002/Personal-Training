package com.example.personaltraining.Controller

import android.content.ContentValues
import com.example.personaltraining.DB.DBHelper
import com.example.personaltraining.Model.Rutina

class CRutina(private val dbHelper: DBHelper) {
    fun createRutina(descripcion: String, dia: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("Descripcion", descripcion)
            put("Dia", dia)
        }
        db.insert("Rutina", null, values)
    }

    fun getAllRutinas(): List<Rutina> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("Rutina", null, null, null, null, null, null)
        val rutinaList = mutableListOf<Rutina>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("Id"))
                val descripcion = getString(getColumnIndexOrThrow("Descripcion"))
                val dia = getString(getColumnIndexOrThrow("Dia"))
                rutinaList.add(Rutina(id, descripcion, dia))
            }
        }
        cursor.close()
        return rutinaList
    }

    fun updateRutina(id: Int, descripcion: String, dia: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("Descripcion", descripcion)
            put("Dia", dia)
        }
        db.update("Rutina", values, "Id = ?", arrayOf(id.toString()))
    }

    fun deleteRutina(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete("Rutina", "Id = ?", arrayOf(id.toString()))
    }
}
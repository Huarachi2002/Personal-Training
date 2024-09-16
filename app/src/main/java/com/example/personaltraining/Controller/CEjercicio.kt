package com.example.personaltraining.Controller

import android.content.ContentValues
import com.example.personaltraining.DB.DBHelper
import com.example.personaltraining.Model.Ejercicio

class CEjercicio(private val dbHelper: DBHelper) {
    fun createEjercicio(descripcion: String, imagenUrl: String?, imagenVideo: String?) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("Descripcion", descripcion)
            put("ImagenUrl", imagenUrl)
            put("ImagenVideo", imagenVideo)
        }
        db.insert("Ejercicio", null, values)
    }

    fun getAllEjercicios(): List<Ejercicio> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("Ejercicio", null, null, null, null, null, null)
        val ejercicioList = mutableListOf<Ejercicio>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("Id"))
                val descripcion = getString(getColumnIndexOrThrow("Descripcion"))
                val imagenUrl = getString(getColumnIndexOrThrow("ImagenUrl"))
                val imagenVideo = getString(getColumnIndexOrThrow("ImagenVideo"))
                ejercicioList.add(Ejercicio(id, descripcion, imagenUrl, imagenVideo))
            }
        }
        cursor.close()
        return ejercicioList
    }

    fun updateEjercicio(id: Int, descripcion: String, imagenUrl: String?, imagenVideo: String?) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("Descripcion", descripcion)
            put("ImagenUrl", imagenUrl)
            put("ImagenVideo", imagenVideo)
        }
        db.update("Ejercicio", values, "Id = ?", arrayOf(id.toString()))
    }

    fun deleteEjercicio(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete("Ejercicio", "Id = ?", arrayOf(id.toString()))
    }
}
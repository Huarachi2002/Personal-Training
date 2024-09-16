package com.example.personaltraining.Controller
import android.content.ContentValues
import com.example.personaltraining.DB.DBHelper
import com.example.personaltraining.Model.Alimentacion

class CAlimentacion(private val dbHelper: DBHelper) {
    fun createAlimentacion(descripcion: String, dia: String, idCliente: Int) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("Descripcion", descripcion)
            put("Dia", dia)
            put("IdCliente", idCliente)
        }
        db.insert("Alimentacion", null, values)
    }

    fun getAllAlimentacion(): List<Alimentacion> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("Alimentacion", null, null, null, null, null, null)
        val alimentacionList = mutableListOf<Alimentacion>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("Id"))
                val descripcion = getString(getColumnIndexOrThrow("Descripcion"))
                val dia = getString(getColumnIndexOrThrow("Dia"))
                val idCliente = getInt(getColumnIndexOrThrow("IdCliente"))
                alimentacionList.add(Alimentacion(id, descripcion, dia, idCliente))
            }
        }
        cursor.close()
        return alimentacionList
    }

    fun updateAlimentacion(id: Int, descripcion: String, dia: String, idCliente: Int) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("Descripcion", descripcion)
            put("Dia", dia)
            put("IdCliente", idCliente)
        }
        db.update("Alimentacion", values, "Id = ?", arrayOf(id.toString()))
    }

    fun deleteAlimentacion(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete("Alimentacion", "Id = ?", arrayOf(id.toString()))
    }
}
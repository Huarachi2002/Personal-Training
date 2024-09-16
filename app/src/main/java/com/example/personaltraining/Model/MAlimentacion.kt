package com.example.personaltraining.Model
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.personaltraining.DB.DBHelper

data class Alimentacion(
    var id: Int? = null,
    var descripcion: String,
    var dia: String,
    var idCliente: Int
)

class MAlimentacion(context: Context) {
    private val dbHelper: DBHelper = DBHelper(context)

    fun crearAlimentacion(alimentacion: Alimentacion): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("descripcion", alimentacion.descripcion)
            put("dia", alimentacion.dia)
            put("idCliente", alimentacion.idCliente)
        }
        return db.insert("alimentacion", null, valores)
    }

    fun actualizarAlimentacion(alimentacion: Alimentacion): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("descripcion", alimentacion.descripcion)
            put("dia", alimentacion.dia)
            put("idCliente", alimentacion.idCliente)
        }
        return db.update("alimentacion", valores, "id = ?", arrayOf(alimentacion.id.toString()))
    }

    fun eliminarAlimentacion(id: Int): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        return db.delete("alimentacion", "id = ?", arrayOf(id.toString()))
    }

    fun obtenerAlimentaciones(): List<Alimentacion> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.query("alimentacion", null, null, null, null, null, null)
        val listaAlimentacion = mutableListOf<Alimentacion>()
        while (cursor.moveToNext()) {
            val alimentacion = Alimentacion(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                dia = cursor.getString(cursor.getColumnIndexOrThrow("dia")),
                idCliente = cursor.getInt(cursor.getColumnIndexOrThrow("idCliente"))
            )
            listaAlimentacion.add(alimentacion)
        }
        cursor.close()
        return listaAlimentacion
    }
}

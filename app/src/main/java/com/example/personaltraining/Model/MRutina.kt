package com.example.personaltraining.Model
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.personaltraining.DB.DBHelper


data class Rutina(
    var id: Int? = null,
    var descripcion: String,
    var dia: String
)

class MRutina(context: Context) {
    private val dbHelper: DBHelper = DBHelper(context)

    fun crearRutina(rutina: Rutina): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("descripcion", rutina.descripcion)
            put("dia", rutina.dia)
        }
        return db.insert("rutina", null, valores)
    }

    fun actualizarRutina(rutina: Rutina): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("descripcion", rutina.descripcion)
            put("dia", rutina.dia)
        }
        return db.update("rutina", valores, "id = ?", arrayOf(rutina.id.toString()))
    }

    fun eliminarRutina(id: Int): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        return db.delete("rutina", "id = ?", arrayOf(id.toString()))
    }

    fun obtenerRutinas(): List<Rutina> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.query("rutina", null, null, null, null, null, null)
        val listaRutinas = mutableListOf<Rutina>()
        while (cursor.moveToNext()) {
            val rutina = Rutina(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                dia = cursor.getString(cursor.getColumnIndexOrThrow("dia"))
            )
            listaRutinas.add(rutina)
        }
        cursor.close()
        return listaRutinas
    }
}

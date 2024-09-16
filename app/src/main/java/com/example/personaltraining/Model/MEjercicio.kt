package com.example.personaltraining.Model
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.personaltraining.DB.DBHelper


data class Ejercicio(
    var id: Int? = null,
    var descripcion: String,
    var imagenUrl: String?,
    var imagenVideo: String?
)

class MEjercicio(context: Context) {
    private val dbHelper: DBHelper = DBHelper(context)

    fun crearEjercicio(ejercicio: Ejercicio): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("descripcion", ejercicio.descripcion)
            put("imagenUrl", ejercicio.imagenUrl)
            put("imagenVideo", ejercicio.imagenVideo)
        }
        return db.insert("ejercicio", null, valores)
    }

    fun actualizarEjercicio(ejercicio: Ejercicio): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("descripcion", ejercicio.descripcion)
            put("imagenUrl", ejercicio.imagenUrl)
            put("imagenVideo", ejercicio.imagenVideo)
        }
        return db.update("ejercicio", valores, "id = ?", arrayOf(ejercicio.id.toString()))
    }

    fun eliminarEjercicio(id: Int): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        return db.delete("ejercicio", "id = ?", arrayOf(id.toString()))
    }

    fun obtenerEjercicios(): List<Ejercicio> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.query("ejercicio", null, null, null, null, null, null)
        val listaEjercicios = mutableListOf<Ejercicio>()
        while (cursor.moveToNext()) {
            val ejercicio = Ejercicio(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                imagenUrl = cursor.getString(cursor.getColumnIndexOrThrow("imagenUrl")),
                imagenVideo = cursor.getString(cursor.getColumnIndexOrThrow("imagenVideo"))
            )
            listaEjercicios.add(ejercicio)
        }
        cursor.close()
        return listaEjercicios
    }
}

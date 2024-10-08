package com.example.personaltraining.Model
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.personaltraining.DB.DBHelper

data class Cliente(
    var id: Int? = null,
    var altura: String,
    var correo: String,
    var nombre: String,
    var peso: Float,
    var sexo: String,
    var telefono: String
)

class MCliente(context: Context) {
    private val dbHelper: DBHelper = DBHelper(context)

    fun crearCliente(cliente: Cliente): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("altura", cliente.altura)
            put("correo", cliente.correo)
            put("nombre", cliente.nombre)
            put("peso", cliente.peso)
            put("sexo", cliente.sexo)
            put("telefono", cliente.telefono)
        }
        return db.insert("cliente", null, valores)
    }

    fun actualizarCliente(cliente: Cliente): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("altura", cliente.altura)
            put("correo", cliente.correo)
            put("nombre", cliente.nombre)
            put("peso", cliente.peso)
            put("sexo", cliente.sexo)
            put("telefono", cliente.telefono)
        }
        return db.update("cliente", valores, "id = ?", arrayOf(cliente.id.toString()))
    }

    fun eliminarCliente(id: Int): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        return db.delete("cliente", "id = ?", arrayOf(id.toString()))
    }

    fun obtenerClientes(): List<Cliente> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.query("cliente", null, null, null, null, null, null)
        val listaClientes = mutableListOf<Cliente>()
        while (cursor.moveToNext()) {
            val cliente = Cliente(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                altura = cursor.getFloat(cursor.getColumnIndexOrThrow("altura")).toString(),
                correo = cursor.getString(cursor.getColumnIndexOrThrow("correo")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                peso = cursor.getFloat(cursor.getColumnIndexOrThrow("peso")),
                sexo = cursor.getString(cursor.getColumnIndexOrThrow("sexo")),
                telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"))
            )
            listaClientes.add(cliente)
        }
        cursor.close()
        return listaClientes
    }
}


package com.example.personaltraining.Controller

import android.content.ContentValues
import com.example.personaltraining.DB.DBHelper
import com.example.personaltraining.Model.Cliente

class CCliente(private val dbHelper: DBHelper) {
    fun createCliente(nombre: String, sexo: String, edad: Int, estatura: Float) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("sexo", sexo)
            put("edad", edad)
            put("altura", estatura)  // Cambiado de "Estatura" a "altura"
        }
        db.insert("cliente", null, values)
    }

    fun getAllClientes(): List<Cliente> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("cliente", null, null, null, null, null, null)
        val clienteList = mutableListOf<Cliente>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val nombre = getString(getColumnIndexOrThrow("nombre"))
                val sexo = getString(getColumnIndexOrThrow("sexo"))
                val edad = getInt(getColumnIndexOrThrow("edad"))
                val altura = getFloat(getColumnIndexOrThrow("altura"))  // Cambiado de getColumnIndexOrThrow("Estatura") a "altura"
                clienteList.add(Cliente(id, altura.toString(),   "", nombre, 0f, sexo, ""))  // Cliente modificado
            }
        }
        cursor.close()
        return clienteList
    }

    fun updateCliente(id: Int, nombre: String, sexo: String, edad: Int, estatura: Float) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("sexo", sexo)
            put("edad", edad)
            put("altura", estatura)  // Cambiado de "Estatura" a "altura"
        }
        db.update("cliente", values, "id = ?", arrayOf(id.toString()))
    }

    fun deleteCliente(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete("cliente", "id = ?", arrayOf(id.toString()))
    }
}
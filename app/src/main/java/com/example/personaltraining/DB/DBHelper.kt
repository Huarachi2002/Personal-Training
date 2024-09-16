package com.example.personaltraining.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "personal_training.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createClienteTable = """
            CREATE TABLE cliente (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                altura REAL,
                correo TEXT,
                nombre TEXT,
                peso REAL,
                sexo TEXT,
                telefono TEXT
            )
        """
        val createAlimentacionTable = """
            CREATE TABLE alimentacion (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                descripcion TEXT,
                dia TEXT,
                idCliente INTEGER,
                FOREIGN KEY (idCliente) REFERENCES cliente(id)
            )
        """
        val createEjercicioTable = """
            CREATE TABLE ejercicio (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                descripcion TEXT,
                imagenUrl TEXT,
                imagenVideo TEXT
            )
        """
        val createRutinaTable = """
            CREATE TABLE rutina (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                descripcion TEXT,
                dia TEXT
            )
        """
        val createRutinaEjercicioTable = """
            CREATE TABLE rutina_ejercicio (
                idRutina INTEGER,
                idEjercicio INTEGER,
                PRIMARY KEY (idRutina, idEjercicio),
                FOREIGN KEY (idRutina) REFERENCES rutina(id),
                FOREIGN KEY (idEjercicio) REFERENCES ejercicio(id)
            )
        """
        db.execSQL(createClienteTable)
        db.execSQL(createAlimentacionTable)
        db.execSQL(createEjercicioTable)
        db.execSQL(createRutinaTable)
        db.execSQL(createRutinaEjercicioTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS cliente")
        db.execSQL("DROP TABLE IF EXISTS alimentacion")
        db.execSQL("DROP TABLE IF EXISTS ejercicio")
        db.execSQL("DROP TABLE IF EXISTS rutina")
        db.execSQL("DROP TABLE IF EXISTS rutina_ejercicio")
        onCreate(db)
    }
}

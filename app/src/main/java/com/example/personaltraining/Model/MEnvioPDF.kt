package com.example.personaltraining.Model

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.example.personaltraining.DB.DBHelper
import java.io.File

data class EnvioPDF(
    val id: Int? = null,  // ID opcional para identificar el envío
    val clienteId: Int,   // ID del cliente al que se envía el PDF
    val rutinaId: Int,    // ID de la rutina asociada con el PDF
    val fechaEnvio: String, // Fecha de envío del PDF
    val estado: String     // Estado del envío: "Enviado", "Pendiente", etc.
)

class MEnvioPDF(context: Context) {
    private val dbHelper: DBHelper = DBHelper(context)

    fun enviarRutinaPdf(cliente: Cliente, rutina: Rutina) {
//        // Generar el PDF con la rutina del cliente
//        val pdfFile = generarPdf(cliente, rutina)
//
//        // Enviar el PDF por WhatsApp
//        enviarPdfPorWhatsApp(cliente, pdfFile)
    }

//    private fun generarPdf(cliente: Cliente, rutina: Rutina): File {
//        // Crear el documento PDF y escribir la rutina
//        val pdfFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "rutina_${cliente.nombre}.pdf")
//
//        try {
//            val document = Document()
//            PdfWriter.getInstance(document, FileOutputStream(pdfFile))
//            document.open()
//
//            // Agregar contenido al PDF
//            document.add(Paragraph("Rutina personalizada para: ${cliente.nombre}"))
//            document.add(Paragraph("Correo: ${cliente.correo}"))
//            document.add(Paragraph("Teléfono: ${cliente.telefono}"))
//            document.add(Paragraph("Rutina del día: ${rutina.dia}"))
//            document.add(Paragraph("Descripción: ${rutina.descripcion}"))
//
//            document.close()
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        return pdfFile
//    }

    private fun enviarPdfPorWhatsApp(cliente: Cliente, pdfFile: File) {
//        // Crear un Intent para enviar el PDF por WhatsApp
//        val intent = Intent(Intent.ACTION_SEND)
//        intent.type = "application/pdf"
//        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(pdfFile))
//        intent.putExtra(Intent.EXTRA_TEXT, "Aquí está tu rutina personalizada, ${cliente.nombre}.")
//
//        // Asegurarse de que exista una aplicación que pueda manejar este Intent
//        if (intent.resolveActivity(context.packageManager) != null) {
//            context.startActivity(Intent.createChooser(intent, "Enviar rutina a ${cliente.nombre}"))
//        }
    }
}
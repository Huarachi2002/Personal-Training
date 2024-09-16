package com.example.personaltraining.Controller

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import com.example.personaltraining.Model.Cliente
import com.example.personaltraining.Model.Rutina
//import com.itextpdf.text.Document
//import com.itextpdf.text.Paragraph
//import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream

class CEnvioPDF(private val context: Context) {

//    fun enviarRutinaPdf(cliente: Cliente, rutina: Rutina) {
//        // Generar el PDF con la rutina del cliente
//        val pdfFile = generarPdf(cliente, rutina)
//
//        // Enviar el PDF por WhatsApp
//        enviarPdfPorWhatsApp(cliente, pdfFile)
//    }

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
        // Crear un Intent para enviar el PDF por WhatsApp
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "application/pdf"
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(pdfFile))
        intent.putExtra(Intent.EXTRA_TEXT, "Aquí está tu rutina personalizada, ${cliente.nombre}.")

        // Asegurarse de que exista una aplicación que pueda manejar este Intent
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(intent, "Enviar rutina a ${cliente.nombre}"))
        }
    }
}
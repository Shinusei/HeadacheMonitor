package com.shinusei.headachemonitor.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.io.File
import java.io.FileOutputStream

/**
 * Export data to file
 *
 * @param context
 * @param data
 */
@Composable
fun ExportDataToFile(context: Context, data: String) {
    /*val db = context.openOrCreateDatabase(data, Context.MODE_PRIVATE, null)
    val cursor: Cursor = db.rawQuery("SELECT * FROM Notes", null)
    try {
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "Headaches.csv")
        val outputStream = FileOutputStream(file)
        while (cursor.moveToNext()){
            val data = "${cursor.getInt(1)}, "
                    "${cursor.getLong(2)}, " +
                    "${cursor.getInt(3)}, " +
                    "${cursor.getInt(4)}, " +
                    "${cursor.getInt(5)}\n"
            outputStream.write(data.toByteArray())
        }

        outputStream.close()
        cursor.close()
    } catch (e: Exception) {
        Log.e("ExportDataToFile", "Error exporting data to file", e)
    }*/

    try {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "Headaches.csv")
            put(MediaStore.MediaColumns.MIME_TYPE, "application/x-sqlite3")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Download/HeadacheMonitor/exports")
        }
        context.contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

    } catch (e: Exception) {
        Log.e("ExportDataToFile", "Error exporting data to file", e)
    }
    //Toast.makeText(LocalContext.current, "Exporting data to file...", Toast.LENGTH_SHORT).show()
}

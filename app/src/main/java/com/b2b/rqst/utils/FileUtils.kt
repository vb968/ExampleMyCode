package com.b2b.rqst.utils

import android.content.Context
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.OpenableColumns
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.regex.Pattern

class FileUtils(val context: Context) {

    fun getFileName(uri: Uri): String {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } finally {
                cursor?.close()
            }
        }
        if (result == null) {
            return (System.currentTimeMillis() / 1000).toString()
        }

        if (Pattern.matches(".*\\p{InCyrillic}.*", result)) {
            val dotIndex = result.lastIndexOf('.')
            val fileName = "File-" + Math.abs(result.hashCode())
            result = if (dotIndex == -1) fileName else fileName + result.substring(dotIndex)
        }

        return result
    }
    fun getSizeKb(uri: Uri): String {
        var size: Long = 0
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    size = cursor.getLong(cursor.getColumnIndex(OpenableColumns.SIZE))
                }
            } finally {
                cursor?.close()
            }
        }
        return (size / 1024).toString()
    }
    @Throws(IOException::class)
    fun readBytes(uri: Uri): ByteArray? =
        context.contentResolver.openInputStream(uri)?.use { it.buffered().readBytes() }

    fun getFile(uri: Uri): File{
        return try {
            val inputStream: InputStream? = if (isVirtualFile(uri)) {
                getInputStreamForVirtualFile(uri, "application/pdf")
            } else {
                context.contentResolver.openInputStream(uri)
            }
            val tempFile = File(context.externalCacheDir, getFileName(uri))
            FileOutputStream(tempFile).use { outputStream ->
                var read: Int
                val bytes = ByteArray(1024)
                while (inputStream!!.read(bytes).also { read = it } != -1) {
                    outputStream.write(bytes, 0, read)
                }
            }
            tempFile
        } catch (e: IOException) {
            e.printStackTrace()
            throw RuntimeException(e)
        }
    }
    private fun isVirtualFile(uri: Uri): Boolean{
        if (!DocumentsContract.isDocumentUri(context, uri)) {
            return false
        }
        val cursor = context.contentResolver.query(uri, arrayOf(DocumentsContract.Document.COLUMN_FLAGS), null, null, null)
        var flags = 0
        if (cursor!!.moveToFirst()) {
            flags = cursor.getInt(0)
        }
        cursor.close()
        return flags and DocumentsContract.Document.FLAG_VIRTUAL_DOCUMENT != 0
    }
    private fun getInputStreamForVirtualFile(uri: Uri, mimeTypeFilter: String): InputStream{
        val resolver = context.contentResolver

        val openableMimeTypes = resolver.getStreamTypes(uri, mimeTypeFilter)

        if (openableMimeTypes.isNullOrEmpty()) {
            throw FileNotFoundException()
        }

        return resolver.openTypedAssetFileDescriptor(uri, openableMimeTypes[0], null)!!.createInputStream()
    }
}
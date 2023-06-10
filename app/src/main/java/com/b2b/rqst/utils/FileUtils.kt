package com.b2b.rqst.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
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
}
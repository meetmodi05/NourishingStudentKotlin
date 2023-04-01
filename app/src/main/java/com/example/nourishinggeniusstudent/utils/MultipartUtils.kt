package com.example.nourishinggeniusstudent.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.NonNull
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

object MultipartUtils {

    private val mediaType = "multipart/form-data".toMediaType()

    fun partText(txt: String): RequestBody {
        return txt.toRequestBody(mediaType)
    }

    @NonNull
    fun prepareFilePart(mContext: Context, fileUri: Uri): MultipartBody.Part {
        val file = File(FilePathUtil.getPath(mContext, fileUri))

        var mediatype: MediaType? = mContext.contentResolver.getType(fileUri)?.toMediaTypeOrNull()
        val filename = if (file.extension.isNullOrBlank()) "${file.name}.jpg" else file.name
        if (mediatype == null) {
            mediatype = "image/jpeg".toMediaTypeOrNull()
        }

        // create RequestBody instance from file
        val requestFile = RequestBody.create(
            mediatype,
            file
        )

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData("document", filename, requestFile)
    }

    fun convertBitmapToFile(mContext: Context, fileName: String, bitmap: Bitmap): File {
        // create a file to write bitmap data
        val file = File(mContext.cacheDir, fileName)
        file.createNewFile()

        // Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos)
        val bitMapData = bos.toByteArray()

        // write the bytes in file
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        try {
            fos?.write(bitMapData)
            fos?.flush()
            fos?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }
}

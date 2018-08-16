package com.example.sirojiddin.testapplication.utils

import android.graphics.BitmapFactory

object FileTypeUtils {
    fun isImage(path: String) : Boolean {
        val options =  BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)
        return (options.outWidth != -1 && options.outHeight != -1)
    }
}
package com.example.sirojiddin.testapplication.utils

import android.graphics.BitmapFactory
import org.intellij.lang.annotations.RegExp
import java.util.regex.Pattern

object FileTypeUtils {
    fun isImage(path: String) : Boolean {
//        val options =  BitmapFactory.Options()
//        options.inJustDecodeBounds = true
//        BitmapFactory.decodeFile(path, options)
//        return (options.outWidth != -1 && options.outHeight != -1)
        val regex = "(?:([^:/?#]+):)?(?://([^/?#]*))?([^?#]*\\.(?:jpg|gif|png))(?:\\?([^#]*))?(?:#(.*))?"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(path)
        return matcher.matches()
    }
}
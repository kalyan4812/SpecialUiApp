package com.example.specialuiapp.scracth_view_pkg

import android.graphics.Bitmap
import java.nio.ByteBuffer


class BitmapUtils {
    /**
     * Compares two bitmaps and gives the percentage of similarity
     *
     * @param bitmap1 input bitmap 1
     * @param bitmap2 input bitmap 2
     * @return a value between 0.0 to 1.0 . Note the method will return 0.0 if either of bitmaps are null nor of same size.
     */
    fun compareEquivalance(bitmap1: Bitmap?, bitmap2: Bitmap?): Float {
        if (bitmap1 == null || bitmap2 == null || bitmap1.width != bitmap2.width || bitmap1.height != bitmap2.height) {
            return 0f
        }
        val buffer1: ByteBuffer = ByteBuffer.allocate(bitmap1.height * bitmap1.rowBytes)
        bitmap1.copyPixelsToBuffer(buffer1)
        val buffer2: ByteBuffer = ByteBuffer.allocate(bitmap2.height * bitmap2.rowBytes)
        bitmap2.copyPixelsToBuffer(buffer2)
        val array1: ByteArray = buffer1.array()
        val array2: ByteArray = buffer2.array()
        val len = array1.size // array1 and array2 will be of some length.
        var count = 0
        for (i in 0 until len) {
            if (array1[i] == array2[i]) {
                count++
            }
        }
        return count.toFloat() / len
    }

    /**
     * Finds the percentage of pixels that do are empty.
     *
     * @param bitmap input bitmap
     * @return a value between 0.0 to 1.0 . Note the method will return 0.0 if either of bitmaps are null nor of same size.
     */
    companion object {
        fun getTransparentPixelPercent(bitmap: Bitmap?): Float {
            if (bitmap == null) {
                return 0f
            }
            val buffer: ByteBuffer = ByteBuffer.allocate(bitmap.height * bitmap.rowBytes)
            bitmap.copyPixelsToBuffer(buffer)
            val array: ByteArray = buffer.array()
            val len = array.size
            var count = 0
            for (b in array) {
                if (b.toInt() == 0) {
                    count++
                }
            }
            return count.toFloat() / len
        }
    }
}
package com.reditus.restaurant_practice_app.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.nio.charset.StandardCharsets
import java.util.Base64

class DataUtils {
    companion object{
        @RequiresApi(Build.VERSION_CODES.O)
        fun plainToBase64(plain: String): String {
            val plainBytes = plain.toByteArray(StandardCharsets.UTF_8)
            val encodedBytes = Base64.getEncoder().encode(plainBytes)
            return String(encodedBytes, StandardCharsets.UTF_8)
        }
    }
}
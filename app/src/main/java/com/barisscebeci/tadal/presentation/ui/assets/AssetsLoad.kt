package com.barisscebeci.tadal.presentation.ui.assets

import android.content.Context
import java.io.IOException

class AssetsLoad {
    fun loadJsonFromAssets(context: Context, fileName: String): String? {
        var json: String? = null
        try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }
}
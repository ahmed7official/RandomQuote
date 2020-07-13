package com.android.randomquote.utils

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import java.lang.reflect.Type


inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = this.edit()
    operation(editor)
    editor.apply()
}

inline operator fun <reified T : Any> SharedPreferences.get(
    key: String,
    defaultValue: T? = null
): T? {
    return when (T::class) {
        String::class -> getString(key, defaultValue as? String ?: "") as T?
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
        Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
        T::class -> getAny(key, T::class.java, defaultValue)
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}

fun <T> SharedPreferences.getAny(key: String, elementType: Type, defaultValue: T? = null): T? {

    val gson = GsonBuilder().create()

    val x: String? = this[key]

    return if (x.isNullOrBlank()) {
        defaultValue
    } else {
        gson.fromJson(x, elementType)
    }
}

fun SharedPreferences.putAny(key: String, any: Any?) {

    val gson = GsonBuilder().create()
    val json = gson.toJson(any)
    this[key] = json
}

operator fun SharedPreferences.set(key: String, value: Any?) {
    when (value) {
        is String? -> edit { putString(key, value) }
        is Int -> edit { putInt(key, value) }
        is Boolean -> edit { putBoolean(key, value) }
        is Float -> edit { putFloat(key, value) }
        is Long -> edit { putLong(key, value) }
        is Any -> this.putAny(key, value)
        else -> throw UnsupportedOperationException("Not yet implemented")
    }


}





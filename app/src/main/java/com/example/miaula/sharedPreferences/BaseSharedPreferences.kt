package com.example.miaula.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

open class BasePreferences {

    protected lateinit var sharedPreferences: SharedPreferences
    private lateinit var gson: Gson

    fun init(context: Context, fileName: String) {
        sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        gson = Gson()
    }

    operator fun <T : Any> set(key: String, value: T): BasePreferences {
        sharedPreferences.edit().putString(key, gson.toJson(value)).apply()
        return this
    }

    fun <T : Any> get(key: String, type: Class<T>): T? {
        val saved = sharedPreferences.getString(key, null)
        val gson = Gson()
        return saved?.let {
            gson.fromJson(
                saved,
                type
            )
        }

    }

    operator fun set(key: String, value: String): BasePreferences {
        sharedPreferences.edit().putString(key, value).apply()
        return this
    }

    operator fun get(key: String): String? = sharedPreferences.getString(key, null)

    operator fun set(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String): Int = sharedPreferences.getInt(key, 0)

    operator fun set(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String): Float = sharedPreferences.getFloat(key, 0f)

    operator fun set(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    fun set(key: String?, value: Long) {
        if (key != null) {
            sharedPreferences.edit().putLong(key, value).apply()
        } else {
            throw RuntimeException("The key value can't be null")
        }
    }

    fun getLong(key: String): Long = sharedPreferences.getLong(key, -1)

    operator fun contains(key: String): Boolean = sharedPreferences.contains(key)

    fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun getAll(): Map<String, String> {
        return sharedPreferences.all.mapValues { it.value as String }
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}

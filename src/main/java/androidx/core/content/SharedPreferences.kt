/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.core.content

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.reflect.KProperty

/**
 * Allows editing of this preference instance with a call to [apply][SharedPreferences.Editor.apply]
 * or [commit][SharedPreferences.Editor.commit] to persist the changes.
 * Default behaviour is [apply][SharedPreferences.Editor.apply].
 * ```
 * prefs.edit {
 *     putString("key", value)
 * }
 * ```
 * To [commit][SharedPreferences.Editor.commit] changes:
 * ```
 * prefs.edit(commit = true) {
 *     putString("key", value)
 * }
 * ```
 */
@SuppressLint("ApplySharedPref")
inline fun SharedPreferences.edit(
    commit: Boolean = false,
    action: SharedPreferences.Editor.() -> Unit
) {
    val editor = edit()
    action(editor)
    if (commit) {
        editor.commit()
    } else {
        editor.apply()
    }
}

inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
    return get(key, defaultValue, T::class.java)
}

inline operator fun <reified T: Any> SharedPreferences.get(key: String): T? {
    return get(key = key, clazz = T::class.java)
}

inline operator fun <reified T: Any> SharedPreferences.set(key: String, value: T?) {
    set(T::class.java, key, value)
}

inline fun <reified T: Any> SharedPreferences.set(key: String) {
    set<T>(key, null)
}

@Suppress("UNCHECKED_CAST")
operator fun <T : Any> SharedPreferences.get(key: String, defaultValue: T? = null, clazz: Class<T>)
        :T? {
    return when (defaultValue){
        null -> when{
            clazz.isAssignableFrom(String::class.java) ->
                    getString(key, null) as T?

            clazz.isAssignableFrom(Set::class.java) ->
                    when {
                        String::class.java.isAssignableFrom(clazz.componentType) ->
                            getStringSet(key, null) as T?
                        else -> {
                            throw IllegalArgumentException(
                                "Illegal nullable Set type ${clazz.canonicalName} for key \"$key\"")
                        }
                    }
                else -> {
                    throw IllegalArgumentException("Illegal nullable type ${clazz.canonicalName}"
                            + "for key \"$key\"")
                }
            }
        else -> when(defaultValue){
            is Float -> getFloat(key, defaultValue) as T
            is Int -> getInt(key, defaultValue) as T
            is Long -> getLong(key, defaultValue) as T
            is String -> getString(key, defaultValue) as T

            is Set<*> -> {
                val componentType = defaultValue::class.java.componentType
                @Suppress("UNCHECKED_CAST") // Checked by reflection.
                when {
                    String::class.java.isAssignableFrom(componentType) ->
                        getStringSet(key, defaultValue as Set<String>) as T
                    else -> {
                        val valueType = componentType.canonicalName
                        throw IllegalArgumentException(
                            "Illegal value Set type $valueType for key \"$key\"")
                    }
                }
            }
            else -> {
                val valueType = defaultValue.javaClass.canonicalName
                throw IllegalArgumentException("Illegal value type $valueType for key \"$key\"")
            }
        }
    }
}


operator fun <T: Any> SharedPreferences.set(clazz: Class<T>, key: String, value: T?) {
    when(value){
        null -> when{
            clazz.isAssignableFrom(String::class.java) ->
                edit { putString(key, null) }

            clazz.isAssignableFrom(Set::class.java) ->
                when {
                    String::class.java.isAssignableFrom(clazz.componentType) ->
                        edit { putStringSet(key, null) }
                    else -> {
                        throw IllegalArgumentException(
                            "Illegal value Set type ${clazz.canonicalName} for key \"$key\"")
                    }
                }
            else -> {
                throw IllegalArgumentException("Illegal value type ${javaClass.canonicalName}"
                        + "for key \"$key\"")
            }
        }
        else -> when(value){
            is Boolean -> edit{ putBoolean(key, value) }
            is Float -> edit{ putFloat(key, value) }
            is Int -> edit { putInt(key, value) }
            is Long -> edit { putLong(key, value) }
            is String -> edit { putString(key, value) }

            is Set<*> -> {
                val componentType = value::class.java.componentType
                @Suppress("UNCHECKED_CAST") // Checked by reflection.
                when {
                    String::class.java.isAssignableFrom(componentType) ->
                        edit { putStringSet(key, value as Set<String>) }
                    else -> {
                        val valueType = componentType.canonicalName
                        throw IllegalArgumentException(
                            "Illegal value array type $valueType for key \"$key\"")
                    }
                }
            }
            else -> {
                val valueType = value::class.java.componentType
                throw IllegalArgumentException("Illegal value type $valueType for key \"$key\"")
            }
        }
    }
}

class SharedPreferencesDelegate<T: Any>(
    private val clazz: Class<T>,
    private val preferenceName: String? = null,
    private val mode: Int = 0,
    private val key: String,
    private val defaultValue: T?):
    ContextDelegateProperty<T> {

    override fun getValue(thisRef: Context, property: KProperty<*>): T? {
        return if(preferenceName != null)
            thisRef.getSharedPreferences(preferenceName, mode)[key, defaultValue, clazz]
        else PreferenceManager.getDefaultSharedPreferences(thisRef)[key, defaultValue, clazz]
    }

    override fun setValue(thisRef: Context, property: KProperty<*>, value: T?) {
        if(preferenceName != null)
            thisRef.getSharedPreferences(preferenceName, mode)[clazz, key] = value
        else PreferenceManager.getDefaultSharedPreferences(thisRef)[clazz, key] = value
    }
}

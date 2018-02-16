/*
 * Copyright (C) 2018 The Android Open Source Project
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

package androidx.os

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import java.io.Serializable
import java.math.BigDecimal
import java.math.BigInteger


///////////////////////////////////////////////////////////////////////////
// parcelable
///////////////////////////////////////////////////////////////////////////

/** kotlin version of parcelable
 * has default implementation for [Parcelable.describeContents] method */
interface KParcelable : Parcelable {
    override fun describeContents() = 0
    override fun writeToParcel(dest: Parcel, flags: Int)
}

/** an implicit and simplified parcelable creator for [Parcelable] classes
 * example of usage in companion object: `@JvmField val CREATOR = parcelableCreator(::YourClass)` */
inline fun <reified T> parcelableCreator(
        crossinline create: (Parcel) -> T) =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel) = create(source)
            override fun newArray(size: Int) = arrayOfNulls<T>(size)
        }

/** an implicit and simplified parcelable creator for [Parcelable] classes which requires classLoader to be built
 * example of usage in companion object: `@JvmField val CREATOR = parcelableClassLoaderCreator(::YourClass)` */
inline fun <reified T> parcelableClassLoaderCreator(
        crossinline create: (Parcel, ClassLoader) -> T) =
        object : Parcelable.ClassLoaderCreator<T> {
            override fun createFromParcel(source: Parcel, loader: ClassLoader) = create(source, loader)
            override fun createFromParcel(source: Parcel) = create(source, this::class.java.classLoader)
            override fun newArray(size: Int) = arrayOfNulls<T>(size)
        }

/** reads parcelable from parcel
 * Used the classLoader of specified reified type */
inline fun <reified T: Parcelable> Parcel.readParcelable(): T? {
    val classLoader = T::class.java.classLoader
    return readParcelable(classLoader)
}

/** writes parcelable to parcel
 * 0 used as parcelableFlags
 * @see Parcel.writeParcelable
 * */
fun Parcel.writeParcelable(parcelable: Parcelable?) = this.writeParcelable(parcelable, 0)

private inline fun <T> Parcel.readNullable(reader: () -> T) =
        if (readInt() != 0) reader() else null

private inline fun <T> Parcel.writeNullable(value: T?, writer: (T) -> Unit) {
    if (value != null) {
        writeInt(1)
        writer(value)
    } else {
        writeInt(0)
    }
}

///////////////////////////////////////////////////////////////////////////
// boolean
///////////////////////////////////////////////////////////////////////////

/** reads boolean from parcel */
fun Parcel.readBoolean() = readInt() != 0

/** writes boolean from parcel */
fun Parcel.writeBoolean(value: Boolean) = writeInt(if (value) 1 else 0)

///////////////////////////////////////////////////////////////////////////
// enum
///////////////////////////////////////////////////////////////////////////

/** read enum with specified reified type from parcel */
inline fun <reified T : Enum<T>> Parcel.readEnum() = readString().let { enumValueOf<T>(it) }

/** writes enum to parcel
 * @param value is enum will be serialized to parcel */
fun <T : Enum<T>> Parcel.writeEnum(value: T?) = value?.let { writeString(value.name) }

/** read nullable enumeration with specified reified type from parcel */
inline fun <reified T : Enum<T>> Parcel.readEnumNullable() = readString()?.let { enumValueOf<T>(it) }

/** writes list of enumeration values to parcel
 * @see writeEnum */
fun <T: Enum<T>> Parcel.writeEnumList(enumList: List<T>) {
    writeInt(enumList.size)
    for (enum in enumList) {
        writeEnum(enum)
    }
}

/** reads list of enumeration values of specified reified type from parcel*/
inline fun <reified T: Enum<T>> Parcel.readEnumList(): MutableList<T> {
    val size = this.readInt()
    val list: MutableList<T> = mutableListOf()
    for (i in 0 until size) {
        list.add(this.readEnum())
    }
    return list
}

///////////////////////////////////////////////////////////////////////////
// big decimal
///////////////////////////////////////////////////////////////////////////

/** reads [BigDecimal] from parcel
 * it is constructed from byte[] and scale parameter */
fun Parcel.readBigDecimal(): BigDecimal? =
        readNullable { BigDecimal(BigInteger(createByteArray()), readInt()) }

/** writes [BigDecimal] to parcel. It is written as byte[] and scale */
fun Parcel.writeBigDecimal(value: BigDecimal?) = writeNullable(value) {
    writeByteArray(it.unscaledValue().toByteArray())
    writeInt(it.scale())
}

///////////////////////////////////////////////////////////////////////////
// CharSequence
///////////////////////////////////////////////////////////////////////////

/** reads [CharSequence] from parcel. Used [TextUtils.CHAR_SEQUENCE_CREATOR] */
fun Parcel.readCharSequence() = readNullable { TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this) }

/** writes [CharSequence] to parcel. Used [TextUtils.writeToParcel]*/
fun Parcel.writeCharSequence(value: CharSequence?, flags: Int) = writeNullable(value) {
    TextUtils.writeToParcel(value, this, flags)
}

///////////////////////////////////////////////////////////////////////////
// map
///////////////////////////////////////////////////////////////////////////

/** writes map to the parcel
 * @param writeKey provided with value function writes key to the parcel manually
 * @param writeValue provided with value function writes value to the parcel manually */
fun <Key,Value> Parcel.writeMap(map: Map<Key,Value>, writeKey: (Key) -> Unit, writeValue: (Value) -> Unit) {
    this.writeInt(map.size)
    map.forEach {(key, value) ->
        writeKey(key)
        writeValue(value)
    }
}

/** reads map from the parcel
 * @param readKey function determines how to read key from the parcel
 * @param readValue function determines how to read value from the parcel
 */
fun <Key,Value> Parcel.readMap(readKey: () -> Key, readValue: () -> Value) : Map<Key,Value> {
    val size = this.readInt()
    val map = hashMapOf<Key, Value>()
    for (i in 0 until size) {
        map[readKey()] = readValue()
    }
    return map
}

///////////////////////////////////////////////////////////////////////////
// other
///////////////////////////////////////////////////////////////////////////

/**
 * Read a typed object from a parcel.
 * Used the classLoader of specified reified type
 * **/
inline fun <reified T> Parcel.readValue(): T? {
    val classLoader = T::class.java.classLoader
    return readValue(classLoader) as T?
}

/** reads serializable with specified reified type */
inline fun <reified T: Serializable> Parcel.readSerializableEx(): T? = readSerializable() as? T

/** reads list of parcelable typed objects
 * @return created and filled list of object with specified reified type */
inline fun <reified T: Parcelable> Parcel.readList(): List<T> {
    val classLoader = T::class.java.classLoader
    val list = mutableListOf<T>()
    readList(list, classLoader)
    return list
}

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

import android.graphics.Rect
import android.os.Binder
import android.os.Bundle
import android.support.test.InstrumentationRegistry
import android.util.Size
import android.util.SizeF
import android.view.View
import androidx.assertThrows
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class BundleTest {
    @Test fun bundleOfValid() {
        val binderValue = Binder()
        val bundleValue = Bundle()
        val charSequenceValue = "hey"
        val sizeValue = Size(1, 1)
        val sizeFValue = SizeF(1f, 1f)
        val parcelableValue = Rect(1, 2, 3, 4)
        val serializableValue = AtomicInteger(1)

        val bundle = bundleOf(
                "null" to null,

                "boolean" to true,
                "byte" to 1.toByte(),
                "char" to 'a',
                "double" to 1.0,
                "float" to 1f,
                "int" to 1,
                "long" to 1L,
                "short" to 1.toShort(),

                "binder" to binderValue,
                "bundle" to bundleValue,
                "charSequence" to charSequenceValue,
                "size" to sizeValue,
                "sizeF" to sizeFValue,
                "parcelable" to parcelableValue,

                "booleanArray" to booleanArrayOf(),
                "byteArray" to byteArrayOf(),
                "charArray" to charArrayOf(),
                "doubleArray" to doubleArrayOf(),
                "floatArray" to floatArrayOf(),
                "intArray" to intArrayOf(),
                "longArray" to longArrayOf(),
                "shortArray" to shortArrayOf(),

                "parcelableArray" to arrayOf(parcelableValue),
                "stringArray" to arrayOf("hey"),
                "charSequenceArray" to arrayOf<CharSequence>("hey"),
                "serializableArray" to arrayOf(serializableValue),

                "serializable" to serializableValue
        )

        assertEquals(28, bundle.size())

        assertNull(bundle["null"])

        assertEquals(true, bundle["boolean"])
        assertEquals(1.toByte(), bundle["byte"])
        assertEquals('a', bundle["char"])
        assertEquals(1.0, bundle["double"])
        assertEquals(1f, bundle["float"])
        assertEquals(1, bundle["int"])
        assertEquals(1L, bundle["long"])
        assertEquals(1.toShort(), bundle["short"])

        assertSame(binderValue, bundle["binder"])
        assertSame(bundleValue, bundle["bundle"])
        assertSame(charSequenceValue, bundle["charSequence"])
        assertSame(sizeValue, bundle["size"])
        assertSame(sizeFValue, bundle["sizeF"])
        assertSame(parcelableValue, bundle["parcelable"])

        assertArrayEquals(booleanArrayOf(), bundle["booleanArray"] as BooleanArray)
        assertArrayEquals(byteArrayOf(), bundle["byteArray"] as ByteArray)
        assertArrayEquals(charArrayOf(), bundle["charArray"] as CharArray)
        assertArrayEquals(doubleArrayOf(), bundle["doubleArray"] as DoubleArray, 0.0)
        assertArrayEquals(floatArrayOf(), bundle["floatArray"] as FloatArray, 0f)
        assertArrayEquals(intArrayOf(), bundle["intArray"] as IntArray)
        assertArrayEquals(longArrayOf(), bundle["longArray"] as LongArray)
        assertArrayEquals(shortArrayOf(), bundle["shortArray"] as ShortArray)

        assertThat(bundle["parcelableArray"] as Array<*>).asList().containsExactly(parcelableValue)
        assertThat(bundle["stringArray"] as Array<*>).asList().containsExactly("hey")
        assertThat(bundle["charSequenceArray"] as Array<*>).asList().containsExactly("hey")
        assertThat(bundle["serializableArray"] as Array<*>).asList().containsExactly(serializableValue)

        assertSame(serializableValue, bundle["serializable"])
    }

    @Test fun bundleOfInvalid() {
        assertThrows<IllegalArgumentException> {
            bundleOf("nope" to View(InstrumentationRegistry.getContext()))
        }.hasMessageThat().isEqualTo("Illegal value type android.view.View for key \"nope\"")

        assertThrows<IllegalArgumentException> {
            bundleOf("nopes" to arrayOf(View(InstrumentationRegistry.getContext())))
        }.hasMessageThat().isEqualTo("Illegal value array type android.view.View for key \"nopes\"")
    }
}

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

package androidx.core.content.res

import android.graphics.Color
import android.support.test.InstrumentationRegistry
import android.support.test.filters.SdkSuppress
import androidx.core.ktx.test.R
import androidx.testutils.assertThrows
import androidx.core.getAttributeSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TypedArrayTest {
    private val context = InstrumentationRegistry.getContext()

    @Test fun boolean() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        assertTrue(array.getBooleanOrThrow(R.styleable.TypedArrayTypes_boolean_present))

        assertThrows<IllegalArgumentException> {
            array.getBooleanOrThrow(R.styleable.TypedArrayTypes_boolean_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test fun color() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        assertEquals(Color.WHITE, array.getColorOrThrow(R.styleable.TypedArrayTypes_color_present))

        assertThrows<IllegalArgumentException> {
            array.getColorOrThrow(R.styleable.TypedArrayTypes_color_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test fun colorStateList() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)
        val stateList = array.getColorStateListOrThrow(R.styleable.TypedArrayTypes_color_present)

        assertEquals(Color.WHITE, stateList.defaultColor)

        assertThrows<IllegalArgumentException> {
            array.getColorStateListOrThrow(R.styleable.TypedArrayTypes_color_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test fun dimension() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        assertEquals(1f, array.getDimensionOrThrow(R.styleable.TypedArrayTypes_dimension_present))

        assertThrows<IllegalArgumentException> {
            array.getDimensionOrThrow(R.styleable.TypedArrayTypes_dimension_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test fun dimensionPixelSize() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        assertEquals(1,
            array.getDimensionPixelSizeOrThrow(R.styleable.TypedArrayTypes_dimension_present))

        assertThrows<IllegalArgumentException> {
            array.getDimensionPixelSizeOrThrow(R.styleable.TypedArrayTypes_dimension_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test fun dimensionPixelOffset() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        assertEquals(1,
            array.getDimensionPixelOffsetOrThrow(R.styleable.TypedArrayTypes_dimension_present))

        assertThrows<IllegalArgumentException> {
            array.getDimensionPixelOffsetOrThrow(R.styleable.TypedArrayTypes_dimension_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test fun drawable() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        assertNotNull(array.getDrawableOrThrow(R.styleable.TypedArrayTypes_drawable_present))

        assertThrows<IllegalArgumentException> {
            array.getDrawableOrThrow(R.styleable.TypedArrayTypes_drawable_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test fun float() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        assertEquals(0.1f, array.getFloatOrThrow(R.styleable.TypedArrayTypes_float_present))

        assertThrows<IllegalArgumentException> {
            array.getFloatOrThrow(R.styleable.TypedArrayTypes_float_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @SdkSuppress(minSdkVersion = 26)
    @Test fun font() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        assertNotNull(array.getFontOrThrow(R.styleable.TypedArrayTypes_font_present))

        assertThrows<IllegalArgumentException> {
            array.getFontOrThrow(R.styleable.TypedArrayTypes_font_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test fun int() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        assertEquals(1, array.getIntOrThrow(R.styleable.TypedArrayTypes_integer_present))

        assertThrows<IllegalArgumentException> {
            array.getIntOrThrow(R.styleable.TypedArrayTypes_integer_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test fun integer() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        assertEquals(1, array.getIntegerOrThrow(R.styleable.TypedArrayTypes_integer_present))

        assertThrows<IllegalArgumentException> {
            array.getIntegerOrThrow(R.styleable.TypedArrayTypes_integer_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test
    fun resourceId() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        assertEquals(
            R.font.inconsolata_regular,
            array.getResourceIdOrThrow(R.styleable.TypedArrayTypes_resource_present)
        )

        assertThrows<IllegalArgumentException> {
            array.getResourceIdOrThrow(R.styleable.TypedArrayTypes_resource_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test fun string() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        assertEquals("Hello", array.getStringOrThrow(R.styleable.TypedArrayTypes_string_present))

        assertThrows<IllegalArgumentException> {
            array.getStringOrThrow(R.styleable.TypedArrayTypes_string_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test fun text() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        assertEquals("Hello", array.getTextOrThrow(R.styleable.TypedArrayTypes_string_present))

        assertThrows<IllegalArgumentException> {
            array.getTextOrThrow(R.styleable.TypedArrayTypes_string_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test fun textArray() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        val text = array.getTextArrayOrThrow(R.styleable.TypedArrayTypes_text_array_present)
        assertEquals("Hello", text[0].toString())
        assertEquals("World", text[1].toString())

        assertThrows<IllegalArgumentException> {
            array.getTextOrThrow(R.styleable.TypedArrayTypes_text_array_absent)
        }.hasMessageThat().isEqualTo("Attribute not defined in set.")
    }

    @Test fun useRecyclesArray() {
        val attrs = context.getAttributeSet(R.layout.typed_array)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TypedArrayTypes)

        val result = array.use {
            it.getBoolean(R.styleable.TypedArrayTypes_boolean_present, false)
        }
        assertTrue(result)

        assertThrows<RuntimeException> {
            array.recycle()
        }
    }
}

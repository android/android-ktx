/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.core.view

import android.support.test.InstrumentationRegistry
import android.support.test.filters.SdkSuppress
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.testutils.assertThrows
import androidx.testutils.fail
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class ViewGroupTest {
    private val context = InstrumentationRegistry.getContext()
    private val viewGroup = LinearLayout(context)

    @Test fun get() {
        val view1 = View(context)
        viewGroup.addView(view1)
        val view2 = View(context)
        viewGroup.addView(view2)

        assertSame(view1, viewGroup[0])
        assertSame(view2, viewGroup[1])

        assertThrows<IndexOutOfBoundsException> {
            viewGroup[-1]
        }.hasMessageThat().isEqualTo("Index: -1, Size: 2")

        assertThrows<IndexOutOfBoundsException> {
            viewGroup[2]
        }.hasMessageThat().isEqualTo("Index: 2, Size: 2")
    }

    @Test fun contains() {
        val view1 = View(context)
        viewGroup.addView(view1)
        assertTrue(view1 in viewGroup)
        assertFalse(view1 !in viewGroup)

        val view2 = View(context)
        assertFalse(view2 in viewGroup)
        assertTrue(view2 !in viewGroup)
    }

    @Test fun plusAssign() {
        assertEquals(0, viewGroup.childCount)

        val view1 = View(context)
        viewGroup += view1
        assertEquals(1, viewGroup.childCount)
        assertSame(view1, viewGroup.getChildAt(0))

        val view2 = View(context)
        viewGroup += view2
        assertEquals(2, viewGroup.childCount)
        assertSame(view2, viewGroup.getChildAt(1))
    }

    @Test fun minusAssign() {
        val view1 = View(context)
        viewGroup.addView(view1)
        val view2 = View(context)
        viewGroup.addView(view2)

        assertEquals(2, viewGroup.childCount)

        viewGroup -= view2
        assertEquals(1, viewGroup.childCount)
        assertSame(view1, viewGroup.getChildAt(0))

        viewGroup -= view1
        assertEquals(0, viewGroup.childCount)
    }

    @Test fun size() {
        assertEquals(0, viewGroup.size)

        viewGroup.addView(View(context))
        assertEquals(1, viewGroup.size)

        viewGroup.addView(View(context))
        assertEquals(2, viewGroup.size)

        viewGroup.removeViewAt(0)
        assertEquals(1, viewGroup.size)
    }

    @Test fun isEmpty() {
        assertTrue(viewGroup.isEmpty())
        viewGroup.addView(View(context))
        assertFalse(viewGroup.isEmpty())
    }

    @Test fun isNotEmpty() {
        assertFalse(viewGroup.isNotEmpty())
        viewGroup.addView(View(context))
        assertTrue(viewGroup.isNotEmpty())
    }

    @Test fun forEach() {
        viewGroup.forEach {
            fail("Empty view group should not invoke lambda")
        }

        val view1 = View(context)
        viewGroup.addView(view1)
        val view2 = View(context)
        viewGroup.addView(view2)

        val views = mutableListOf<View>()
        viewGroup.forEach {
            views += it
        }
        assertThat(views).containsExactly(view1, view2)
    }

    @Test fun forEachIndexed() {
        viewGroup.forEachIndexed { _, _ ->
            fail("Empty view group should not invoke lambda")
        }

        val view1 = View(context)
        viewGroup.addView(view1)
        val view2 = View(context)
        viewGroup.addView(view2)

        val views = mutableListOf<View>()
        viewGroup.forEachIndexed { index, view ->
            assertEquals(index, views.size)
            views += view
        }
        assertThat(views).containsExactly(view1, view2)
    }

    @Test fun iterator() {
        val view1 = View(context)
        viewGroup.addView(view1)
        val view2 = View(context)
        viewGroup.addView(view2)

        val iterator = viewGroup.iterator()
        assertTrue(iterator.hasNext())
        assertSame(view1, iterator.next())
        assertTrue(iterator.hasNext())
        assertSame(view2, iterator.next())
        assertFalse(iterator.hasNext())
        assertThrows<IndexOutOfBoundsException> {
            iterator.next()
        }
    }

    @Test fun iteratorRemoving() {
        val view1 = View(context)
        viewGroup.addView(view1)
        val view2 = View(context)
        viewGroup.addView(view2)

        val iterator = viewGroup.iterator()

        assertSame(view1, iterator.next())
        iterator.remove()
        assertFalse(view1 in viewGroup)
        assertEquals(1, viewGroup.childCount)

        assertSame(view2, iterator.next())
        iterator.remove()
        assertFalse(view2 in viewGroup)
        assertEquals(0, viewGroup.childCount)
    }

    @Test fun iteratorForEach() {
        val views = listOf(View(context), View(context))
        views.forEach(viewGroup::addView)

        var index = 0
        for (view in viewGroup) {
            assertSame(views[index++], view)
        }
    }

    @Test fun children() {
        val views = listOf(View(context), View(context), View(context))
        views.forEach { viewGroup.addView(it) }

        viewGroup.children.forEachIndexed { index, child ->
            assertSame(views[index], child)
        }
    }

    @Test fun setMargins() {
        val layoutParams = ViewGroup.MarginLayoutParams(100, 200)
        layoutParams.setMargins(42)
        assertEquals(42, layoutParams.leftMargin)
        assertEquals(42, layoutParams.topMargin)
        assertEquals(42, layoutParams.rightMargin)
        assertEquals(42, layoutParams.bottomMargin)
    }

    @Test fun updateMargins() {
        val layoutParams = ViewGroup.MarginLayoutParams(100, 200)
        layoutParams.updateMargins(top = 10, right = 20)
        assertEquals(0, layoutParams.leftMargin)
        assertEquals(10, layoutParams.topMargin)
        assertEquals(20, layoutParams.rightMargin)
        assertEquals(0, layoutParams.bottomMargin)
    }

    @Test fun updateMarginsNoOp() {
        val layoutParams = ViewGroup.MarginLayoutParams(100, 200)
        layoutParams.setMargins(10, 20, 30, 40)
        layoutParams.updateMargins()
        assertEquals(10, layoutParams.leftMargin)
        assertEquals(20, layoutParams.topMargin)
        assertEquals(30, layoutParams.rightMargin)
        assertEquals(40, layoutParams.bottomMargin)
    }

    @SdkSuppress(minSdkVersion = 17)
    @Test fun updateMarginsRelative() {
        val layoutParams = ViewGroup.MarginLayoutParams(100, 200)
        layoutParams.updateMarginsRelative(start = 10, end = 20)
        assertEquals(0, layoutParams.leftMargin)
        assertEquals(0, layoutParams.topMargin)
        assertEquals(0, layoutParams.rightMargin)
        assertEquals(0, layoutParams.bottomMargin)
        assertEquals(10, layoutParams.marginStart)
        assertEquals(20, layoutParams.marginEnd)
        assertTrue(layoutParams.isMarginRelative)
    }

    @SdkSuppress(minSdkVersion = 17)
    @Test fun updateMarginsRelativeNoOp() {
        val layoutParams = ViewGroup.MarginLayoutParams(100, 200)
        layoutParams.setMargins(10, 20, 30, 40)
        layoutParams.updateMarginsRelative()
        assertEquals(10, layoutParams.leftMargin)
        assertEquals(20, layoutParams.topMargin)
        assertEquals(30, layoutParams.rightMargin)
        assertEquals(40, layoutParams.bottomMargin)
        assertEquals(10, layoutParams.marginStart)
        assertEquals(30, layoutParams.marginEnd)
    }
}

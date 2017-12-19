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

package androidx.view

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.assertThrows
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ViewGroupTest {
    private val context = RuntimeEnvironment.application
    private val viewGroup = LinearLayout(context)
    private val layoutParams = ViewGroup.MarginLayoutParams(100, 200)

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

    @Test fun children() {
        val view1 = View(context)
        viewGroup.addView(view1)
        val view2 = View(context)
        viewGroup.addView(view2)

        val children = viewGroup.children().iterator()
        assertTrue(children.hasNext())
        assertSame(view1, children.next())
        assertTrue(children.hasNext())
        assertSame(view2, children.next())
        assertFalse(children.hasNext())
        assertThrows<IndexOutOfBoundsException> {
            children.next()
        }
    }

    @Test fun updateMargin() {
        layoutParams.updateMargins(top = 10, right = 20)
        assertEquals(0, layoutParams.leftMargin)
        assertEquals(10, layoutParams.topMargin)
        assertEquals(20, layoutParams.rightMargin)
        assertEquals(0, layoutParams.bottomMargin)
    }

    @Test fun updateMarginNoOp() {
        layoutParams.setMargins(10, 20, 30, 40)
        layoutParams.updateMargins()
        assertEquals(10, layoutParams.leftMargin)
        assertEquals(20, layoutParams.topMargin)
        assertEquals(30, layoutParams.rightMargin)
        assertEquals(40, layoutParams.bottomMargin)
    }
}

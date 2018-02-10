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

package androidx.delegate

import android.support.test.filters.SdkSuppress
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import java.lang.ref.ReferenceQueue

@SdkSuppress(minSdkVersion = 19)
class ReferenceTest {
    @Test fun assignSoftReference() {
        var variable by SoftRef("foo")
        assertEquals("foo", variable)
        variable = "bar"
        assertNotEquals("foo", variable)
        assertEquals("bar", variable)
    }

    @Test fun notAssignSoftReference() {
        var variable by SoftRef<Int>()
        assertNull(variable)
        variable = 11
        assertNotNull(variable)
        assertEquals(11, variable)
    }

    @Test fun gcSoftReference() {
        var data: TestData? = TestData()
        val variable by SoftRef(data)
        assertNotNull(variable)
        data = null
        System.gc()
        assertNotNull(variable)
    }

    @Test fun assignWeakReference() {
        var variable by WeakRef("foo")
        assertEquals("foo", variable)
        variable = "bar"
        assertNotEquals("foo", variable)
        assertEquals("bar", variable)
    }

    @Test fun notAssignWeakReference() {
        var variable by WeakRef<String>()
        assertNull(variable)
        variable = "bar"
        assertNotNull(variable)
        assertEquals("bar", variable)
    }

    @Test fun gcWeakReference() {
        val variable by WeakRef(TestData())
        System.gc()
        assertNull(variable)
    }

    @Test fun assignPhantomReference() {
        var variable by PhantomRef("foo", ReferenceQueue())
        assertNull(variable)
    }

    @Test fun notAssignPhantomReference() {
        var variable by PhantomRef<String>(queue = ReferenceQueue())
        assertNull(variable)
    }

    private data class TestData(var id: Int = 0)
}
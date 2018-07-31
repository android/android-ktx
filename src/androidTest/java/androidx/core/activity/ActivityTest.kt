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

package androidx.core.activity

import android.content.Intent
import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import androidx.core.TextExtraActivity
import androidx.testutils.assertThrows
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

class ActivityTest {

    @JvmField
    @Rule
    val rule = ActivityTestRule(TextExtraActivity::class.java, false, false)

    @Test
    fun get() {
        val activity = launchExtraActivity {
            putExtra(TextExtraActivity.EXTRA_STRING, "test")
            putExtra(TextExtraActivity.EXTRA_INT, 1)
            putExtra(TextExtraActivity.EXTRA_NULLABLE_INT, 2)
            putExtra(TextExtraActivity.EXTRA_INT_DEFAULT, 3)
            putExtra(TextExtraActivity.EXTRA_NULLABLE_INT_DEFAULT, 4)
        }
        assertEquals("test", activity.stringExtra)
        assertEquals(1, activity.intExtra)
        assertEquals(2, activity.nullableIntExtra)
        assertEquals(3, activity.intDefaultExtra)
        assertEquals(4, activity.nullableIntDefaultExtra)
    }

    @Test
    fun getMissing() {
        val activity = launchExtraActivity()
        assertThrows<IllegalArgumentException> { activity.stringExtra }
        assertThrows<IllegalArgumentException> { activity.intExtra }
        assertNull(activity.nullableIntExtra)
        assertEquals(TextExtraActivity.DEFAULT_INT, activity.intDefaultExtra)
        assertEquals(TextExtraActivity.DEFAULT_NULLABLE_INT, activity.nullableIntDefaultExtra)
    }

    @Test
    fun getNull() {
        val activity = launchExtraActivity {
            putExtra(TextExtraActivity.EXTRA_STRING, null as String?)
            putExtra(TextExtraActivity.EXTRA_INT, null as Int?)
            putExtra(TextExtraActivity.EXTRA_NULLABLE_INT, null as Int?)
            putExtra(TextExtraActivity.EXTRA_INT_DEFAULT, null as Int?)
            putExtra(TextExtraActivity.EXTRA_NULLABLE_INT_DEFAULT, null as Int?)
        }
        assertThrows<IllegalArgumentException> { activity.stringExtra }
        assertThrows<IllegalArgumentException> { activity.intExtra }
        assertNull(activity.nullableIntExtra)
        assertEquals(TextExtraActivity.DEFAULT_INT, activity.intDefaultExtra)
        assertEquals(TextExtraActivity.DEFAULT_NULLABLE_INT, activity.nullableIntDefaultExtra)
    }

    @Test
    fun getWrongType() {
        val activity = launchExtraActivity {
            putExtra(TextExtraActivity.EXTRA_STRING, Bundle())
            putExtra(TextExtraActivity.EXTRA_INT, Bundle())
            putExtra(TextExtraActivity.EXTRA_NULLABLE_INT, Bundle())
            putExtra(TextExtraActivity.EXTRA_INT_DEFAULT, Bundle())
            putExtra(TextExtraActivity.EXTRA_NULLABLE_INT_DEFAULT, Bundle())
        }
        assertThrows<IllegalArgumentException> { activity.stringExtra }
        assertThrows<IllegalArgumentException> { activity.intExtra }
        assertThrows<IllegalArgumentException> { activity.nullableIntExtra }
        assertEquals(TextExtraActivity.DEFAULT_INT, activity.intDefaultExtra)
        assertEquals(TextExtraActivity.DEFAULT_NULLABLE_INT, activity.nullableIntDefaultExtra)
    }

    private fun launchExtraActivity(configure: Intent.() -> Unit = {}): TextExtraActivity {
        val intent = Intent().apply(configure)
        return rule.launchActivity(intent)
    }
}

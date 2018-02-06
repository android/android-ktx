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

package androidx.app

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import androidx.kotlin.TestActivity
import androidx.kotlin.test.R
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FragmentManagerTest {
    @JvmField @Rule val rule = ActivityTestRule<TestActivity>(TestActivity::class.java)

    private lateinit var fragmentManager: FragmentManager
    private val fragmentA = Fragment()

    @Before fun setup() {
        fragmentManager = rule.activity.fragmentManager

        transactOnUiThread {
            add(R.id.root, fragmentA, "fragmentA")
        }
    }

    @Test fun transactAdd() {
        assertEquals(fragmentManager.findFragmentByTag("fragmentA"), fragmentA)
    }

    @Test fun transactRemove() {
        transactOnUiThread {
            remove(fragmentA)
        }

        assertNull(fragmentManager.findFragmentByTag("fragmentA"))
    }

    @Test fun transactReplace() {
        val fragmentB = Fragment()

        transactOnUiThread {
            replace(R.id.root, fragmentB, "fragmentB")
        }

        assertNull(fragmentManager.findFragmentByTag("fragmentA"))
        assertEquals(fragmentManager.findFragmentByTag("fragmentB"), fragmentB)
    }

    private fun transactOnUiThread(transaction: FragmentTransaction.() -> Unit) {
        rule.runOnUiThread {
            fragmentManager.transact {
                transaction()
            }
        }

        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }
}
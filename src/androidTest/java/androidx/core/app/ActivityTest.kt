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

package androidx.core.app

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import androidx.core.TestActivity
import org.junit.Rule
import org.junit.Test

class ActivityTest{

    @JvmField @Rule
    val rule = ActivityTestRule<TestActivity>(TestActivity::class.java)

    @Test fun bindIntentStringExtra() {

        rule.launchActivity(Intent().apply {  })

      //  val booleanExtra by bindExtra("key", false);


       // bindExtra("key", false);

       // Assert.assertEquals("test_value", preferences.getString("test_key1", null))
       // Assert.assertEquals(100, preferences.getInt("test_key2", 0))
    }
}

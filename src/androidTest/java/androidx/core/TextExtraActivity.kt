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

package androidx.core

import android.app.Activity
import androidx.core.activity.extra

class TextExtraActivity : Activity() {

    val stringExtra by extra<String>(EXTRA_STRING)
    val intExtra by extra<Int>(EXTRA_INT)
    val nullableIntExtra by extra<Int?>(EXTRA_NULLABLE_INT)
    val intDefaultExtra by extra<Int>(EXTRA_INT_DEFAULT) { DEFAULT_INT }
    val nullableIntDefaultExtra by extra<Int?>(EXTRA_NULLABLE_INT_DEFAULT) { DEFAULT_NULLABLE_INT }

    companion object {
        const val EXTRA_STRING = "string"
        const val EXTRA_INT = "int"
        const val EXTRA_NULLABLE_INT = "nullable_int"
        const val EXTRA_INT_DEFAULT = "int_default"
        const val EXTRA_NULLABLE_INT_DEFAULT = "nullable_int_default"

        const val DEFAULT_INT: Int = 42
        val DEFAULT_NULLABLE_INT: Int? = null
    }
}
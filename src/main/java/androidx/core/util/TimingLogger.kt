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

package androidx.core.util

import android.util.TimingLogger

/**
 * Resets the timing logger instance before timing each of the given splits and subsequently
 * dumping the results to the log output.
 */
@Suppress("NOTHING_TO_INLINE") // aliases to other public API
inline fun TimingLogger.log(vararg splits: Pair<String, () -> Unit>) {
    this.reset()
    splits.forEach {
        it.second()
        this.addSplit(it.first)
    }
    this.dumpToLog()
}

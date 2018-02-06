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

import android.os.Build.VERSION_CODES.LOLLIPOP
import android.os.PersistableBundle
import android.support.annotation.RequiresApi
import androidx.os.persistable.PersistableBundlePair

/**
 * Returns a new [PersistableBundle] with the given key/value pairs as elements.
 *
 * @throws IllegalArgumentException When a value is not a supported type of [PersistableBundle].
 */
@RequiresApi(LOLLIPOP)
fun persistableBundleOf(vararg pairs: PersistableBundlePair) = PersistableBundle(pairs.size).apply {
    pairs.forEach { it.putFunction(this) }
}

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

@file:Suppress("NOTHING_TO_INLINE")

package androidx.core.util

import java.math.BigDecimal

/**
 * Round to given number of decimal places of [Double]
 * ```
 * example  x = 11.11111
 * x.roundToDecimalPlaces(2) will return 1.11
 */
inline fun Double.roundToDecimalPlaces(digits: Int):Double =
    BigDecimal(this).setScale(digits, BigDecimal.ROUND_HALF_UP).toDouble()


/*
 * Copyright (C) 2017 The Android Open Source Project
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

package androidx.time

import android.support.annotation.RequiresApi
import java.time.Period

/**
 * Return the years component of this [Period].
 *
 * @see Period.getYears
 */
@RequiresApi(26)
inline operator fun Period.component1(): Int = years

/**
 * Return the months component of this [Period].
 *
 * @see Period.getMonths
 */
@RequiresApi(26)
inline operator fun Period.component2(): Int = months

/**
 * Return the days component of this [Period].
 *
 * @see Period.getDays
 */
@RequiresApi(26)
inline operator fun Period.component3(): Int = days

/**
 * Negate this [Period].
 *
 * @see Period.negated
 */
@RequiresApi(26)
inline operator fun Period.unaryMinus(): Period = negated()

/**
 * Multiply this [Period] by the `multiplicand`.
 *
 * @see Period.multipliedBy
 */
@RequiresApi(26)
inline operator fun Period.times(multiplicand: Int): Period = multipliedBy(multiplicand)

/**
 * Return a [Period] representing this value in days.
 *
 * @see Period.ofDays
 */
@RequiresApi(26)
inline fun Int.days(): Period = Period.ofDays(this)

/**
 * Return a [Period] representing this value in months.
 *
 * @see Period.ofMonths
 */
@RequiresApi(26)
inline fun Int.months(): Period = Period.ofMonths(this)

/**
 * Return a [Period] representing this value in years.
 *
 * @see Period.ofYears
 */
@RequiresApi(26)
inline fun Int.years(): Period = Period.ofYears(this)

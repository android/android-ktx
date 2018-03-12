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

@file:Suppress("NOTHING_TO_INLINE")

package androidx.core.graphics

import android.graphics.Point
import android.graphics.PointF

/**
 * Returns the x coordinate of this point.
 *
 * This method allows to use destructuring declarations when working with points,
 * for example:
 * ```
 * val (x, y) = myPoint
 * ```
 */
inline operator fun Point.component1() = this.x

/**
 * Returns the y coordinate of this point.
 *
 * This method allows to use destructuring declarations when working with points,
 * for example:
 * ```
 * val (x, y) = myPoint
 * ```
 */
inline operator fun Point.component2() = this.y

/**
 * Returns the x coordinate of this point.
 *
 * This method allows to use destructuring declarations when working with points,
 * for example:
 * ```
 * val (x, y) = myPoint
 * ```
 */
inline operator fun PointF.component1() = this.x

/**
 * Returns the y coordinate of this point.
 *
 * This method allows to use destructuring declarations when working with points,
 * for example:
 * ```
 * val (x, y) = myPoint
 * ```
 */
inline operator fun PointF.component2() = this.y

/**
 * Offsets this point by the specified point and returns the result as a new point.
 */
inline operator fun Point.plus(p: Point): Point {
    return Point(x, y).apply {
        offset(p.x, p.y)
    }
}

/**
 * Offsets this point by the specified point and returns the result as a new point.
 */
inline operator fun PointF.plus(p: PointF): PointF {
    return PointF(x, y).apply {
        offset(p.x, p.y)
    }
}

/**
 * Offsets this point by the specified amount on both X and Y axis and returns the
 * result as a new point.
 */
inline operator fun Point.plus(xy: Int): Point {
    return Point(x, y).apply {
        offset(xy, xy)
    }
}

/**
 * Offsets this point by the specified amount on both X and Y axis and returns the
 * result as a new point.
 */
inline operator fun PointF.plus(xy: Float): PointF {
    return PointF(x, y).apply {
        offset(xy, xy)
    }
}

/**
 * Offsets this point by the negation of the specified point and returns the result
 * as a new point.
 */
inline operator fun Point.minus(p: Point): Point {
    return Point(x, y).apply {
        offset(-p.x, -p.y)
    }
}

/**
 * Offsets this point by the negation of the specified point and returns the result
 * as a new point.
 */
inline operator fun PointF.minus(p: PointF): PointF {
    return PointF(x, y).apply {
        offset(-p.x, -p.y)
    }
}

/**
 * Offsets this point by the negation of the specified amount on both X and Y axis and
 * returns the result as a new point.
 */
inline operator fun Point.minus(xy: Int): Point {
    return Point(x, y).apply {
        offset(-xy, -xy)
    }
}

/**
 * Offsets this point by the negation of the specified amount on both X and Y axis and
 * returns the result as a new point.
 */
inline operator fun PointF.minus(xy: Float): PointF {
    return PointF(x, y).apply {
        offset(-xy, -xy)
    }
}

/**
 * Returns a new point representing the negation of this point.
 */
inline operator fun Point.unaryMinus() = Point(-x, -y)

/**
 * Returns a new point representing the negation of this point.
 */
inline operator fun PointF.unaryMinus() = PointF(-x, -y)

/**
 * Returns a [PointF] representation of this point.
 */
inline fun Point.toPointF() = PointF(this)

/**
 * Returns a [Point] representation of this point.
 */
inline fun PointF.toPoint() = Point(x.toInt(), y.toInt())

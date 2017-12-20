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

package androidx.graphics

import android.graphics.Point
import android.graphics.PointF
import android.graphics.Rect
import android.graphics.RectF
import kotlin.math.ceil
import kotlin.math.floor

/**
 * Returns "left", the first component of the rectangle.
 *
 * This method allows to use destructuring declarations when working with rectangles,
 * for example:
 * ```
 * val (left, top, right, bottom) = myRectangle
 * ```
 */
inline operator fun Rect.component1() = this.left

/**
 * Returns "top", the first component of the rectangle.
 *
 * This method allows to use destructuring declarations when working with rectangles,
 * for example:
 * ```
 * val (left, top, right, bottom) = myRectangle
 * ```
 */
inline operator fun Rect.component2() = this.top

/**
 * Returns "right", the first component of the rectangle.
 *
 * This method allows to use destructuring declarations when working with rectangles,
 * for example:
 * ```
 * val (left, top, right, bottom) = myRectangle
 * ```
 */
inline operator fun Rect.component3() = this.right

/**
 * Returns "bottom", the first component of the rectangle.
 *
 * This method allows to use destructuring declarations when working with rectangles,
 * for example:
 * ```
 * val (left, top, right, bottom) = myRectangle
 * ```
 */
inline operator fun Rect.component4() = this.bottom

/**
 * Returns "left", the first component of the rectangle.
 *
 * This method allows to use destructuring declarations when working with rectangles,
 * for example:
 * ```
 * val (left, top, right, bottom) = myRectangle
 * ```
 */
inline operator fun RectF.component1() = this.left

/**
 * Returns "top", the first component of the rectangle.
 *
 * This method allows to use destructuring declarations when working with rectangles,
 * for example:
 * ```
 * val (left, top, right, bottom) = myRectangle
 * ```
 */
inline operator fun RectF.component2() = this.top

/**
 * Returns "right", the first component of the rectangle.
 *
 * This method allows to use destructuring declarations when working with rectangles,
 * for example:
 * ```
 * val (left, top, right, bottom) = myRectangle
 * ```
 */
inline operator fun RectF.component3() = this.right

/**
 * Returns "bottom", the first component of the rectangle.
 *
 * This method allows to use destructuring declarations when working with rectangles,
 * for example:
 * ```
 * val (left, top, right, bottom) = myRectangle
 * ```
 */
inline operator fun RectF.component4() = this.bottom

/**
 * Performs the union of this rectangle and the specified rectangle and returns
 * the result as a new rectangle.
 */
inline operator fun Rect.plus(r: Rect): Rect {
    val l = Rect(this)
    l.union(r)
    return l
}

/**
 * Performs the union of this rectangle and the specified rectangle and returns
 * the result as a new rectangle.
 */
inline operator fun RectF.plus(r: RectF): RectF {
    val l = RectF(this)
    l.union(r)
    return l
}

/**
 * Returns a new rectangle representing this rectangle offset by the specified
 * amount on both X and Y axis.
 */
inline operator fun Rect.plus(xy: Int): Rect {
    val l = Rect(this)
    l.offset(xy, xy)
    return l
}

/**
 * Returns a new rectangle representing this rectangle offset by the specified
 * amount on both X and Y axis.
 */
inline operator fun RectF.plus(xy: Float): RectF {
    val l = RectF(this)
    l.offset(xy, xy)
    return l
}

/**
 * Returns a new rectangle representing this rectangle offset by the specified
 * point.
 */
inline operator fun Rect.plus(xy: Point): Rect {
    val l = Rect(this)
    l.offset(xy.x, xy.y)
    return l
}

/**
 * Returns a new rectangle representing this rectangle offset by the specified
 * point.
 */
inline operator fun RectF.plus(xy: PointF): RectF {
    val l = RectF(this)
    l.offset(xy.x, xy.y)
    return l
}

/**
 * Returns true if the specified point is inside the rectangle.
 * The left and top are considered to be inside, while the right and bottom are not.
 * This means that for a point to be contained: left <= x < right and top <= y < bottom.
 * An empty rectangle never contains any point.
 */
inline operator fun Rect.contains(p: Point) = contains(p.x, p.y)

/**
 * Returns true if the specified point is inside the rectangle.
 * The left and top are considered to be inside, while the right and bottom are not.
 * This means that for a point to be contained: left <= x < right and top <= y < bottom.
 * An empty rectangle never contains any point.
 */
inline operator fun RectF.contains(p: PointF) = contains(p.x, p.y)

/**
 * Returns a [RectF] representation of this rectangle.
 */
fun Rect.toRectF(): RectF = RectF(this)

/**
 * Returns a [Rect] representation of this rectangle. The resulting rect will be sized such
 * that this rect can fit within it.
 */
fun RectF.toRect(): Rect {
    return Rect(floor(left).toInt(), floor(top).toInt(),
            ceil(right).toInt(), ceil(bottom).toInt())
}
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
import android.graphics.Rect
import android.graphics.Region
import android.graphics.RegionIterator

/**
 * Return true if the region contains the specified [Point].
 */
inline operator fun Region.contains(p: Point) = contains(p.x, p.y)

/**
 * Return the union of this region and the specified [Rect] as a new region.
 */
inline operator fun Region.plus(r: Rect): Region {
    return Region(this).apply {
        union(r)
    }
}

/**
 * Return the union of this region and the specified region as a new region.
 */
inline operator fun Region.plus(r: Region): Region {
    return Region(this).apply {
        op(r, Region.Op.UNION)
    }
}

/**
 * Return the difference of this region and the specified [Rect] as a new region.
 */
inline operator fun Region.minus(r: Rect): Region {
    return Region(this).apply {
        op(r, Region.Op.DIFFERENCE)
    }
}

/**
 * Return the difference of this region and the specified region as a new region.
 */
inline operator fun Region.minus(r: Region): Region {
    return Region(this).apply {
        op(r, Region.Op.DIFFERENCE)
    }
}

/**
 * Returns the negation of this region as a new region.
 */
inline operator fun Region.unaryMinus(): Region {
    return Region(bounds).apply {
        op(this@unaryMinus, Region.Op.DIFFERENCE)
    }
}

/**
 * Returns the negation of this region as a new region.
 */
inline operator fun Region.not() = -this

/**
 * Return the union of this region and the specified [Rect] as a new region.
 */
inline infix fun Region.and(r: Rect) = this + r

/**
 * Return the union of this region and the specified region as a new region.
 */
inline infix fun Region.and(r: Region) = this + r

/**
 * Return the intersection of this region and the specified [Rect] as a new region.
 */
inline infix fun Region.or(r: Rect): Region {
    return Region(this).apply {
        op(r, Region.Op.INTERSECT)
    }
}

/**
 * Return the intersection of this region and the specified region as a new region.
 */
inline infix fun Region.or(r: Region): Region {
    return Region(this).apply {
        op(r, Region.Op.INTERSECT)
    }
}

/**
 * Return the union minus the intersection of this region and the specified [Rect]
 * as a new region.
 */
inline infix fun Region.xor(r: Rect): Region {
    return Region(this).apply {
        op(r, Region.Op.XOR)
    }
}

/**
 * Return the union minus the intersection of this region and the specified region
 * as a new region.
 */
inline infix fun Region.xor(r: Region): Region {
    return Region(this).apply {
        op(r, Region.Op.XOR)
    }
}

/** Performs the given action on each rect in this region. */
inline fun Region.forEach(action: (rect: Rect) -> Unit) {
    val iterator = RegionIterator(this)
    while (true) {
        val r = Rect()
        if (!iterator.next(r)) {
            break
        }
        action(r)
    }
}

/** Returns an [Iterator] over the rects in this region. */
operator fun Region.iterator() = object : Iterator<Rect> {
    private val iterator = RegionIterator(this@iterator)
    private val rect = Rect()
    private var hasMore = iterator.next(rect)

    override fun hasNext() = hasMore

    override fun next(): Rect {
        if (hasMore) {
            val r = Rect(rect)
            hasMore = iterator.next(rect)
            return r
        }
        throw IndexOutOfBoundsException()
    }
}

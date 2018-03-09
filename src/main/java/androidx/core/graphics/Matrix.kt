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

package androidx.core.graphics

import android.graphics.Matrix

/**
 * Multiplies this [Matrix] by another matrix and returns the result as
 * a new matrix.
 */
inline operator fun Matrix.times(m: Matrix) = Matrix(this).apply { preConcat(m) }

/**
 * Returns the 9 values of this [Matrix] as a new array of floats.
 */
inline fun Matrix.values() = FloatArray(9).apply { getValues(this) }

/**
 * Creates a translation matrix with the translation amounts [tx] and [ty]
 * respectively on the `x` and `y` axis.
 */
fun translationMatrix(tx: Float = 0.0f, ty: Float = 0.0f) = Matrix().apply { setTranslate(tx, ty) }

/**
 * Creates a scale matrix with the scale factor [sx] and [sy] respectively on the
 * `x` and `y` axis.
 */
fun scaleMatrix(sx: Float = 1.0f, sy: Float = 1.0f) = Matrix().apply { setScale(sx, sy) }

/**
 * Creates a rotation matrix, defined by a rotation angle in degrees around the pivot
 * point located at the coordinates ([px], [py]).
 */
fun rotationMatrix(degrees: Float, px: Float = 0.0f, py: Float = 0.0f) =
    Matrix().apply { setRotate(degrees, px, py) }

/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.view

import android.graphics.Bitmap
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.support.annotation.RequiresApi
import android.support.v4.view.ViewCompat
import android.view.View
import android.view.ViewTreeObserver
import androidx.graphics.applyCanvas

/**
 * Performs the given action when this view is next laid out.
 *
 * @see doOnLayout
 */
inline fun View.doOnNextLayout(crossinline action: (view: View) -> Unit) {
    addOnLayoutChangeListener(
            object : View.OnLayoutChangeListener {
                override fun onLayoutChange(
                    view: View, left: Int, top: Int, right: Int, bottom: Int,
                    oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int
                ) {
                    view.removeOnLayoutChangeListener(this)
                    action(view)
                }
            }
    )
}

/**
 * Performs the given action when this view is laid out. If the view has been laid out, the action
 * will be performed straight away, otherwise the action will be performed after the
 * view is laid out.
 *
 * @see doOnNextLayout
 */
inline fun View.doOnLayout(crossinline action: (view: View) -> Unit) {
    if (ViewCompat.isLaidOut(this)) {
        action(this)
    } else {
        doOnNextLayout {
            action(it)
        }
    }
}

/**
 * Performs the given action when the view tree is about to be drawn.
 */
inline fun View.doOnPreDraw(crossinline action: (view: View) -> Unit) {
    val vto = viewTreeObserver
    vto.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    action(this@doOnPreDraw)
                    when {
                        vto.isAlive -> vto.removeOnPreDrawListener(this)
                        else -> viewTreeObserver.removeOnPreDrawListener(this)
                    }
                    return true
                }
            }
    )
}

/**
 * Performs the given action when the global layout state or the visibility of
 * views within the view tree changes
 */
inline fun View.doAfterDraw(crossinline action: (view: View) -> Unit) {
    val vto = viewTreeObserver
    vto.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    action(this@doAfterDraw)
                    if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
                        when {
                            vto.isAlive -> vto.removeOnGlobalLayoutListener(this)
                            else -> viewTreeObserver.removeOnGlobalLayoutListener(this)
                        }
                    } else {
                        when {
                            vto.isAlive -> vto.removeGlobalOnLayoutListener(this)
                            else -> viewTreeObserver.removeGlobalOnLayoutListener(this)
                        }
                    }
                }
            }
    )
}

/**
 * Updates this view's relative padding. This version of the method allows using named parameters
 * to just set one or more axes.
 *
 * @see View.setPaddingRelative
 */
@RequiresApi(17)
fun View.updatePaddingRelative(
    start: Int = paddingStart,
    top: Int = paddingTop,
    end: Int = paddingEnd,
    bottom: Int = paddingBottom
) {
    setPaddingRelative(start, top, end, bottom)
}

/**
 * Updates this view's padding. This version of the method allows using named parameters
 * to just set one or more axes.
 *
 * @see View.setPadding
 */
fun View.updatePadding(
    left: Int = paddingLeft,
    top: Int = paddingTop,
    right: Int = paddingRight,
    bottom: Int = paddingBottom
) {
    setPadding(left, top, right, bottom)
}

/**
 * Sets the view's padding. This version of the method sets all axes to the provided size.
 *
 * @see View.setPadding
 */
fun View.setPadding(size: Int) {
    setPadding(size, size, size, size)
}

/**
 * Version of [View.postDelayed] which re-orders the parameters, allowing the action to be placed
 * outside of parentheses.
 *
 * ```
 * view.postDelayed(200) {
 *     doSomething()
 * }
 * ```
 *
 * @return the created Runnable
 */
fun View.postDelayed(delayInMillis: Long, action: () -> Unit): Runnable {
    return Runnable(action).apply {
        postDelayed(this, delayInMillis)
    }
}

/**
 * Version of [View.postOnAnimationDelayed] which re-orders the parameters, allowing the action
 * to be placed outside of parentheses.
 *
 * ```
 * view.postOnAnimationDelayed(16) {
 *     doSomething()
 * }
 * ```
 *
 * @return the created Runnable
 */
@RequiresApi(16)
fun View.postOnAnimationDelayed(delayInMillis: Long, action: () -> Unit): Runnable {
    return Runnable(action).apply {
        postOnAnimationDelayed(this, delayInMillis)
    }
}

/**
 * Return a [Bitmap] representation of this [View].
 *
 * The resulting bitmap will be the same width and height as this view's current layout
 * dimensions. This does not take into account any transformations such as scale or translation.
 *
 * Note, this will use the software rendering pipeline to draw the view to the bitmap. This may
 * result with different drawing to what is rendered on a hardware accelerated canvas (such as
 * the device screen).
 *
 * If this view has not been laid out this method will throw a [IllegalStateException].
 *
 * @param config Bitmap config of the desired bitmap. Defaults to [Config.ARGB_8888].
 */
fun View.toBitmap(config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap {
    if (!ViewCompat.isLaidOut(this)) {
        throw IllegalStateException("View needs to be laid out before calling toBitmap()")
    }
    return Bitmap.createBitmap(width, height, config).applyCanvas(::draw)
}

/** Show current view */
fun View.show() {
    visibility = View.VISIBLE
}

/** Hide current view */
fun View.hide() {
    visibility = View.INVISIBLE
}

/** Gone current view */
fun View.gone() {
    visibility = View.GONE
}

/** Gone current view or show, depending on the condition */
fun View.visibleIf(prediction: Boolean?) {
    if (prediction == true) show() else gone()
}

/** Return state of current view
 *
 *  @return the visibility for current view
 */
fun View.isVisible() = visibility == View.VISIBLE
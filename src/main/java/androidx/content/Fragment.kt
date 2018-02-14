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

package androidx.content

import android.app.Activity
import android.app.Fragment
import android.graphics.Rect
import android.support.annotation.RequiresApi

/**
 * Screen [Rect]
 */
val Fragment.screenRect: Rect
    get() {
        val displayRectangle = android.graphics.Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(displayRectangle)
        return displayRectangle
    }

/**
 * Opens filesystem file picker. Result is delivered into onActivityResult
 * Requires runtime permission [android.Manifest.permission.READ_EXTERNAL_STORAGE]
 */
@RequiresApi(19)
fun Fragment.getFileFromFilesystem(requestCode: Int = 22, type: String = "image/*") {
    val intent = android.content.Intent(android.content.Intent.ACTION_OPEN_DOCUMENT)
    intent.addCategory(android.content.Intent.CATEGORY_OPENABLE)
    intent.type = type
    startActivityForResult(intent, requestCode)
}

/**
 * Opens default camera. Result is delivered into onActivityResult
 * Requires runtime permission [android.Manifest.permission.CAMERA]
 */
fun Fragment.getImageFromCamera(requestCode: Int = 23) =
    startActivityForResult(
        android.content.Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE),
        requestCode
    )

/**
 * Convenient function that gives access to specific [Activity] context inside the [Fragment]
 *
 * ```
 * Example :
 * withActivity<MainActivity>{
 *    supportActionBar?.title = "Hello World"
 * }
 * ```
 */
@Throws(ClassCastException::class)
inline fun <reified T : Activity?> Fragment.withActivity(block: T.() -> Unit) {
    if (activity != null && isAdded) {
        block(activity as T)
    }
}


/**
 * Convenient function that gives access to specific [Activity] context inside the [Fragment]
 * and returns value
 *
 * ```
 * Example :
 * val title = withActivity<MainActivity, String>{
 *    supportActionBar?.title.toString()
 * }
 * printLn(title) // MainActivity
 * ```
 */
@Throws(ClassCastException::class)
inline fun <reified T : Activity?, reified R : Any?> Fragment.withActivity(block: T.() -> R): R? =
    if (activity != null && isAdded) {
        block(activity as T)
    } else {
        null
    }
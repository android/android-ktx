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
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.provider.MediaStore
import android.support.annotation.RequiresApi

/**
 * Screen [Rect]
 */
val Activity.screenRect: Rect
    get() {
        val displayRectangle = Rect()
        window.decorView.getWindowVisibleDisplayFrame(displayRectangle)
        return displayRectangle
    }

/**
 * Opens link in new window of an app that can view Internet links
 */
fun Activity.viewLink(link: String = "http://www.google.com") {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
}

/**
 * Opens filesystem file picker. Result is delivered into onActivityResult
 * Requires runtime permission [android.Manifest.permission.READ_EXTERNAL_STORAGE]
 */
@RequiresApi(19)
fun Activity.getFileFromFilesystem(requestCode: Int = 22, type: String = "image/*") {
    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
    intent.addCategory(Intent.CATEGORY_OPENABLE)
    intent.type = type
    startActivityForResult(intent, requestCode)
}

/**
 * Opens default camera. Result is delivered into onActivityResult
 * Requires runtime permission [android.Manifest.permission.CAMERA]
 */
fun Activity.getImageFromCamera(requestCode: Int = 23) =
    startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), requestCode)

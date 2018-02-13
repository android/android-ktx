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

package androidx.content

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.support.annotation.AttrRes
import android.support.annotation.LayoutRes
import android.support.annotation.RequiresApi
import android.support.annotation.StyleRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.net.MalformedURLException
import java.nio.charset.Charset

/**
 * Return the handle to a system-level service by class.
 *
 * The return type of this function intentionally uses a
 * [platform type](https://kotlinlang.org/docs/reference/java-interop.html#null-safety-and-platform-types)
 * to allow callers to decide whether they require a service be present or can tolerate its absence.
 *
 * @see Context.getSystemService(Class)
 */
@RequiresApi(23)
@Suppress("HasPlatformType") // Intentionally propagating platform type with unknown nullability.
inline fun <reified T> Context.systemService() = getSystemService(T::class.java)

/**
 * Executes [block] on a [TypedArray] receiver. The [TypedArray] holds the attribute
 * values in [set] that are listed in [attrs]. In addition, if the given [AttributeSet]
 * specifies a style class (through the `style` attribute), that style will be applied
 * on top of the base attributes it defines.
 *
 * @param set The base set of attribute values.
 * @param attrs The desired attributes to be retrieved. These attribute IDs must be
 *              sorted in ascending order.
 * @param defStyleAttr An attribute in the current theme that contains a reference to
 *                     a style resource that supplies defaults values for the [TypedArray].
 *                     Can be 0 to not look for defaults.
 * @param defStyleRes A resource identifier of a style resource that supplies default values
 *                    for the [TypedArray], used only if [defStyleAttr] is 0 or can not be found
 *                     in the theme. Can be 0 to not look for defaults.
 *
 * @see Context.obtainStyledAttributes
 * @see android.content.res.Resources.Theme.obtainStyledAttributes
 */
inline fun Context.withStyledAttributes(
    set: AttributeSet? = null,
    attrs: IntArray,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
    block: TypedArray.() -> Unit
) {
    val typedArray = obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes)
    try {
        typedArray.block()
    } finally {
        typedArray.recycle()
    }
}

/**
 * Executes [block] on a [TypedArray] receiver. The [TypedArray] holds the the values
 * defined by the style resource [resourceId] which are listed in [attrs].
 *
 * @param attrs The desired attributes. These attribute IDs must be sorted in ascending order.
 *
 * @see Context.obtainStyledAttributes
 * @see android.content.res.Resources.Theme.obtainStyledAttributes
 */
inline fun Context.withStyledAttributes(
    @StyleRes resourceId: Int,
    attrs: IntArray,
    block: TypedArray.() -> Unit
) {
    val typedArray = obtainStyledAttributes(resourceId, attrs)
    try {
        typedArray.block()
    } finally {
        typedArray.recycle()
    }
}

/**
 * Screen [Point] dimens
 */
val Context.screenPointDimens: Point
    get() {
        val point = Point()
        (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
            .getSize(point)
        return point
    }

/**
 * Screen aspect ration
 */
val Context.screenAspectRatio: Float
    get() {
        val point = screenPointDimens
        return point.x.toFloat() / point.y.toFloat()
    }

/**
 * Return string asset for given [path] and [charset]
 *
 * Throws [IOException]
 */
@Throws(IOException::class)
fun Context.getStringAsset(path: String, charset: String = "UTF-8"): String? {
    val inputStream = javaClass.classLoader.getResourceAsStream("assets/$path")
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    return String(buffer, Charset.forName(charset))
}

/**
 * Return image asset for given [path] and [charset] like [Bitmap]
 */
fun Context.getImageAssetAsBitmap(path: String): Bitmap?{
    val ims = javaClass.classLoader.getResourceAsStream("assets/$path")
    return BitmapFactory.decodeStream(ims)
}

/**
 * Return image asset for given [path] and [charset] like [Drawable]
 */
fun Context.getImageAssetAsDrawable(path: String): Drawable?{
    val ims = javaClass.classLoader.getResourceAsStream("assets/$path")
    return Drawable.createFromStream(ims, path)
}

/**
 * Creates temp file from given bitmap. All exception are caught - returns null in case of exception
 */
fun Context.createTempFileFromBitmap(
    image: Bitmap?,
    fileName: String = "image_${System.currentTimeMillis()}.jpg"
): File? {
    try {
        val fileTemp = File(externalCacheDir, fileName)
        fileTemp.createNewFile()
        val fos = FileOutputStream(fileTemp)
        image?.compress(Bitmap.CompressFormat.JPEG, 90, fos)
        fos.close()
        return fileTemp
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        return null
    } catch (e: MalformedURLException) {
        e.printStackTrace()
        return null
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }
}

/**
 * Creates bitmap from Uri of image file
 *
 * Throws [FileNotFoundException] and [IOException]
 */
@Suppress("HasPlatformType")
@Throws(FileNotFoundException::class, IOException::class)
fun Context.createBitmapFromFile(file: Uri) = MediaStore.Images.Media.getBitmap(contentResolver, file)

/**
 * Inflates view for given [layout]
 *
 * Throws [IOException]
 */
fun Context.inflateView(
    @LayoutRes layout: Int, root: ViewGroup? = null,
    attachToRoot: Boolean = false
): View =
    LayoutInflater.from(this).inflate(layout, root, attachToRoot)
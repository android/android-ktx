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
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.provider.MediaStore
import android.support.test.InstrumentationRegistry
import android.support.test.filters.SdkSuppress
import android.test.mock.MockContext
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.getAttributeSet
import androidx.kotlin.test.R
import androidx.os.toUri
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.Charset

class ContextTest {
    private val context = InstrumentationRegistry.getContext()

    @SdkSuppress(minSdkVersion = 23)
    @Test fun systemService() {
        var lookup: Class<*>? = null
        val context = object : MockContext() {
            override fun getSystemServiceName(serviceClass: Class<*>): String? {
                lookup = serviceClass
                return if (serviceClass == Unit::class.java) "unit" else null
            }

            override fun getSystemService(name: String): Any? {
                return if (name == "unit") Unit else null
            }
        }
        val actual = context.systemService<Unit>()
        assertEquals(Unit::class.java, lookup)
        assertSame(Unit, actual)
    }

    @Test fun withStyledAttributes() {
        context.withStyledAttributes(attrs = intArrayOf(android.R.attr.textColorPrimary)) {
            val resourceId = getResourceId(0, -1)
            assertTrue(resourceId != 1)
        }

        context.withStyledAttributes(
            android.R.style.Theme_Light,
            intArrayOf(android.R.attr.textColorPrimary)
        ) {
            val resourceId = getResourceId(0, -1)
            assertTrue(resourceId != 1)
        }

        val attrs = context.getAttributeSet(R.layout.test_attrs)
        context.withStyledAttributes(attrs, R.styleable.SampleAttrs) {
            assertTrue(getInt(R.styleable.SampleAttrs_sample, -1) != -1)
        }

        context.withStyledAttributes(attrs, R.styleable.SampleAttrs, 0, 0) {
            assertTrue(getInt(R.styleable.SampleAttrs_sample, -1) != -1)
        }
    }

    @Test
    fun testScreenPointDimens() {
        val point = Point()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
            .getSize(point)
        val testPoint = context.screenPointDimens
        assertEquals(point.x, testPoint.x)
        assertEquals(point.y, testPoint.y)
    }

    @Test
    fun testScreenAspectRatio() {
        val point = context.screenPointDimens
        val aspectRatio = point.x.toFloat() / point.y.toFloat()
        val testAspectRatio = context.screenAspectRatio
        assertEquals(aspectRatio, testAspectRatio)
    }

    @Test
    fun testGetStringAsset() {
        val inputStream = javaClass.classLoader.getResourceAsStream("assets/test_text.txt")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val resultString = String(buffer, Charset.forName("UTF-8"))
        val testString = context.getStringAsset("test_text.txt")
        assertEquals(resultString, testString)
    }

    @Test
    fun testGetImageAssetAsBitmap() {
        val ims = javaClass.classLoader.getResourceAsStream("assets/red.png")
        val bitmap = BitmapFactory.decodeStream(ims)
        val testBitmap = context.getImageAssetAsBitmap("red.png")
        assertEquals(bitmap, testBitmap)
    }

    @Test
    fun testImageAssetAsDrawable() {
        val ims = javaClass.classLoader.getResourceAsStream("assets/red.png")
        val drawable = Drawable.createFromStream(ims, "red.png")
        val testDrawable = context.getImageAssetAsDrawable("red.png")
        assertEquals(drawable, testDrawable)
    }

    @Test
    fun testCreateTempFileFromBitmapAndViceVersa() {
        val image = context.getImageAssetAsBitmap("red.png")
        val fileTemp = File(context.externalCacheDir, "test.jpg")
        fileTemp.createNewFile()
        val fos = FileOutputStream(fileTemp)
        image?.compress(Bitmap.CompressFormat.JPEG, 90, fos)
        fos.close()
        val file = fileTemp
        val testFileUri = context.createTempFileFromBitmap(image)
        assertEquals(file.exists(), testFileUri?.exists())
        assertEquals(file.length(), testFileUri?.length())
        val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, file.toUri())
        val testBitmap = context.createBitmapFromFile(file.toUri())
        assertEquals(bitmap, testBitmap)
        context.externalCacheDir.delete()
    }

    @Test
    fun testInflateView() {
        val view = LayoutInflater.from(context).inflate(R.layout.test_activity, null, false)
        val testView = context.inflateView(R.layout.test_activity)
        assertEquals(view, testView)
    }
}

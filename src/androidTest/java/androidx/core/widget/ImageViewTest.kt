package androidx.core.widget

import android.graphics.Bitmap
import org.junit.Before
import android.support.test.InstrumentationRegistry
import android.widget.ImageView
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ImageViewTest {

    private lateinit var fakeBitmap: Bitmap
    private val context = InstrumentationRegistry.getContext()

    @Before
    fun setup() {
        val dim = 100
        fakeBitmap = Bitmap.createBitmap(dim, dim, Bitmap.Config.ARGB_8888)
    }

    @Test
    fun setBitmapTest() {
        val imageView = ImageView(context)
        imageView.bitmap = fakeBitmap
        assertTrue(fakeBitmap.sameAs(imageView.bitmap))
    }

    @Test
    fun emptyBitmapTest() {
        val imageView = ImageView(context)
        assertNull(imageView.bitmap)
    }

}
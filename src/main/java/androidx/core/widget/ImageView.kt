package androidx.core.widget

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView


/**
 * Returns Bitmap if set to imageview if not than it will return null
 *
 * ```
 * val myBitmap:Bitmap? = imageView.bitmap
 * ```
 *
 * Setting a bitmap to this property using [ImageView.setImageBitmap]
 *
 * ```
 * imageView.bitmap = myBitmap
 * ```
 */
inline var ImageView.bitmap: Bitmap?
    get() {
        val drawable = drawable as? BitmapDrawable
        return drawable?.bitmap
    }
    set(value) {
        setImageBitmap(value)
    }

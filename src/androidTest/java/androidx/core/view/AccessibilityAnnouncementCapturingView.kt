package androidx.core.view

import android.content.Context
import android.view.View

class AccessibilityAnnouncementCapturingView(context: Context?) : View(context) {

    var announcement: CharSequence? = null

    override fun announceForAccessibility(text: CharSequence?) {
        super.announceForAccessibility(text)
        announcement = text
    }
}

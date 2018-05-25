package androidx.core.animation

import android.support.test.InstrumentationRegistry
import android.support.test.annotation.UiThreadTest
import android.support.test.filters.SdkSuppress
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewPropertyAnimator
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewPropertyAnimatorTest {
    private val context = InstrumentationRegistry.getContext()
    private val view = View(context)

    private lateinit var animator: ViewPropertyAnimator

    @Before
    fun before() {
        view.alpha = 0f
        animator = view.animate().alpha(1f).setDuration(0)
    }

    @UiThreadTest
    @Test fun testDoOnStart() {
        var called = false
        animator.doOnStart {
            called = true
        }

        animator.start()
        Assert.assertTrue(called)
    }

    @UiThreadTest
    @Test fun testDoOnEnd() {
        var called = false
        animator.doOnEnd {
            called = true
        }

        animator.start()
        animator.cancel()
        Assert.assertTrue(called)
    }

    @UiThreadTest
    @Test fun testDoOnCancel() {
        var cancelCalled = false
        animator.doOnCancel {
            cancelCalled = true
        }

        animator.start()
        animator.cancel()
        Assert.assertTrue(cancelCalled)
    }

    @UiThreadTest
    @SdkSuppress(minSdkVersion = 19)
    @Test fun testDoOnUpdate() {
        var called = false
        animator.doOnUpdate {
            called = true
        }

        animator.start()
        Assert.assertTrue(called)
    }
}
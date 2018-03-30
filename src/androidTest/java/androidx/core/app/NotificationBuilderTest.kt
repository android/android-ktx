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

package androidx.core.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import android.support.test.uiautomator.Until
import android.support.v4.app.NotificationCompat
import androidx.core.kotlin.test.R
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class NotificationBuilderTest {
    private val testChanelId = "test_channel"

    @Before fun setUp() {

        //Add notification channel for O.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                testChanelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            (InstrumentationRegistry.getContext()
                .getSystemService(android.content.Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    @Test fun checkShow() {
        val testNotificationTitle = "Test notification title"
        //Assign notification
        NotificationCompat.Builder(InstrumentationRegistry.getContext(), testChanelId)
            .setContentText("Test notification content")
            .setContentTitle(testNotificationTitle)
            .setSmallIcon(R.drawable.ic_notification)
            .show(System.currentTimeMillis().toInt())

        //Open notifications
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.openNotification()
        device.wait(Until.hasObject(By.pkg("com.android.systemui")), 10000)

        //Find our notification with above title and check if it exists?
        val notificationStackScroller = UiSelector()
            .packageName("com.android.systemui")
            .resourceId("com.android.systemui:id/notification_stack_scroller")
        val notificationSelectorUiObject = device
            .findObject(notificationStackScroller)
            .getChild(UiSelector().text(testNotificationTitle))
        assertTrue(notificationSelectorUiObject.exists())

    }
}

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

import android.app.NotificationManager
import android.support.v4.app.NotificationCompat
import android.app.NotificationChannel
import android.os.Build



/**
 * Displays the notification with given [notificationId].
 */

fun NotificationCompat.Builder.show(notificationId: Int) {
    val nm = mContext.getSystemService(android.content.Context.NOTIFICATION_SERVICE)
            as NotificationManager

    nm.notify(notificationId, build())
}

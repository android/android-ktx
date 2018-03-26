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

package androidx.core.io

import android.webkit.MimeTypeMap
import androidx.core.text.urlEncode
import java.io.File

/**
 * Returns the MIME type (content type) of this [File] based on its extension.
 *
 * @see MimeUtils
 * @return The MIME type for the files' extension or null if there is none.
 */
val File.mimeTypeFromExtension: String?
    get() = MimeTypeMap.getFileExtensionFromUrl(path.urlEncode())
        ?.apply { MimeTypeMap.getSingleton().getMimeTypeFromExtension(toLowerCase()) }


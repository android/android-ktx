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

package androidx.security

import android.os.Build
import java.security.Key
import javax.crypto.Cipher

private fun getCipherProvider(): String {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) "AndroidOpenSSL"
    else "AndroidKeyStoreBCWorkaround"
}

fun cipherOf(
    transformation: String = "RSA/ECB/PKCS1Padding",
    provider: String = getCipherProvider()
): Cipher = Cipher.getInstance(transformation, provider)

fun Cipher.encrypt(data: String, key: Key): ByteArray {
    return encrypt(data.toByteArray(), key)
}

fun Cipher.encrypt(data: ByteArray, key: Key): ByteArray {
    init(Cipher.ENCRYPT_MODE, key)
    return doFinal(data)
}

fun Cipher.decrypt(data: String, key: Key?): ByteArray {
    return decrypt(data.toByteArray(), key)
}

fun Cipher.decrypt(data: ByteArray, key: Key?): ByteArray {
    init(Cipher.DECRYPT_MODE, key)
    return doFinal(data)
}

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

import java.security.KeyPair
import java.security.KeyStore
import java.security.PrivateKey

fun keyStoreOf(type: String = "AndroidKeyStore"): KeyStore {
    val keyStore = KeyStore.getInstance(type)
    keyStore.load(null)
    return keyStore
}

fun KeyStore.getKeyPair(alias: String, password: CharArray? = null): KeyPair? {
    val privateKey = getKey(alias, password) as? PrivateKey ?: return null
    val publicKey = getCertificate(alias)?.publicKey ?: return null
    return KeyPair(publicKey, privateKey)
}

fun KeyStore.removeKeyPair(alias: String) {
    deleteEntry(alias)
}
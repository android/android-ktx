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

@file:Suppress("DEPRECATION")

package androidx.security

import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.support.annotation.RequiresApi
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.util.Calendar
import javax.security.auth.x500.X500Principal

@RequiresApi(18)
fun keyPairGeneratorOf(
    context: Context,
    alias: String,
    algorithm: String = "RSA",
    provider: String = "AndroidKeyStore"
): KeyPairGenerator {
    return KeyPairGenerator.getInstance(algorithm, provider).apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initGeneratorWithKeyGenParameterSpec(this, alias)
        } else {
            initGeneratorWithKeyPairGeneratorSpec(this, alias, context)
        }
    }
}

@RequiresApi(23)
private fun initGeneratorWithKeyGenParameterSpec(generator: KeyPairGenerator, alias: String) {
    val builder = KeyGenParameterSpec.Builder(
        alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    )
        .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)

    generator.initialize(builder.build())
}

@RequiresApi(18)
private fun initGeneratorWithKeyPairGeneratorSpec(
    generator: KeyPairGenerator,
    alias: String,
    context: Context
) {
    val startDate = Calendar.getInstance()
    val endDate = Calendar.getInstance()
    endDate.add(Calendar.YEAR, 20)
    val builder = KeyPairGeneratorSpec.Builder(context)
        .setAlias(alias)
        .setSerialNumber(BigInteger.ONE)
        .setSubject(X500Principal("CN=$alias CA Certificate"))
        .setStartDate(startDate.time)
        .setEndDate(endDate.time)

    generator.initialize(builder.build())
}
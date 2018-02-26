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
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.support.annotation.RequiresApi
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.util.Calendar
import javax.security.auth.x500.X500Principal

class KeyPairGeneratorBuilder {
    var algorithm: String = "RSA"
    var provider: String = "AndroidKeyStore"

    fun build(block: (KeyPairGenerator) -> Unit): KeyPairGenerator {
        return KeyPairGenerator.getInstance(algorithm, provider)
            .apply { block(this) }
    }

    @RequiresApi(18)
    fun build(spec: KeyPairGeneratorSpec): KeyPairGenerator {
        return KeyPairGenerator.getInstance(algorithm, provider)
            .apply { initialize(spec) }
    }

    @RequiresApi(18)
    fun build(alias: String, context: Context): KeyPairGenerator {
        return KeyPairGenerator.getInstance(algorithm, provider)
            .apply { initialize(getKeyPairGeneratorSpec(alias, context)) }
    }

    @RequiresApi(23)
    fun build(spec: KeyGenParameterSpec): KeyPairGenerator {
        return KeyPairGenerator.getInstance(algorithm, provider)
            .apply { initialize(spec) }
    }

    @RequiresApi(23)
    fun build(alias: String): KeyPairGenerator {
        return KeyPairGenerator.getInstance(algorithm, provider)
            .apply { initialize(getKeyGenParameterSpec(alias)) }
    }

    @RequiresApi(23)
    private fun getKeyGenParameterSpec(alias: String): KeyGenParameterSpec {
        return KeyGenParameterSpec.Builder(
            alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
            .build()
    }

    @RequiresApi(18)
    private fun getKeyPairGeneratorSpec(alias: String, context: Context): KeyPairGeneratorSpec {
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance().apply { add(Calendar.YEAR, 20) }
        return KeyPairGeneratorSpec.Builder(context)
            .setAlias(alias)
            .setSerialNumber(BigInteger.ONE)
            .setSubject(X500Principal("CN=$alias CA Certificate"))
            .setStartDate(startDate.time)
            .setEndDate(endDate.time)
            .build()
    }
}

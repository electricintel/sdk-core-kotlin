package network.xyo.sdkcorekotlin.signing.algorithms.ecc.secp256k.keys

import network.xyo.sdkcorekotlin.signing.algorithms.ecc.XyoUncompressedEcPublicKey
import java.security.AlgorithmParameters
import java.security.spec.ECGenParameterSpec
import java.security.spec.ECParameterSpec

abstract class XyoSecp256K1CompressedPublicKey : XyoUncompressedEcPublicKey() {
    override val ecSpec: ECParameterSpec
        get() = ecPramSpec

    override val id: ByteArray
        get() = byteArrayOf(major, minor)

    companion object : XyoUncompressedEcPublicKeyCreator() {
        override val ecPramSpec: ECParameterSpec
            get() {
                val parameters = AlgorithmParameters.getInstance("EC")
                parameters.init(ECGenParameterSpec("secp256k1"))
                return parameters.getParameterSpec(ECParameterSpec::class.java)
            }

        override val minor: Byte
            get() = 0x01
    }
}
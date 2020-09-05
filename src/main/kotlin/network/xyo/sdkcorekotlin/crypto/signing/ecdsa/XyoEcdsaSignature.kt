package network.xyo.sdkcorekotlin.crypto.signing.ecdsa

import network.xyo.sdkcorekotlin.schemas.XyoInterpret
import network.xyo.sdkcorekotlin.schemas.XyoSchemas
import network.xyo.sdkobjectmodelkotlin.structure.XyoObjectStructure
import java.math.BigInteger
import java.nio.ByteBuffer

/**
 * A base class for all EC signature operations.
 */
open class XyoEcdsaSignature : XyoObjectStructure {

    constructor(r : BigInteger, s : BigInteger, byteArray: ByteArray? = null): super(byteArray) {
        this.r = r
        this.s = s
        this.bytes = getObjectEncoded(XyoSchemas.EC_SIGNATURE, encode())
    }

    val r : BigInteger
    val s : BigInteger

    private fun encode () : ByteArray {
        val encodedR = r.toByteArray()
        val encodedS = s.toByteArray()
        val buffer = ByteBuffer.allocate(2 + encodedR.size + encodedS.size)
        buffer.put(encodedR.size.toByte())
        buffer.put(encodedR)
        buffer.put(encodedS.size.toByte())
        buffer.put(encodedS)
        return buffer.array()
    }

    companion object : XyoInterpret {
        class XyoRAndS(val r : BigInteger, val s : BigInteger)

        protected fun getRAndS(byteArray: ByteArray) : XyoRAndS {
            val sizeOfR = byteArray[0].toInt()
            val sizeOfS = (byteArray[sizeOfR + 1]).toInt()
            val r = BigInteger(1, byteArray.copyOfRange(1, sizeOfR + 1))
            val s = BigInteger(1, byteArray.copyOfRange(sizeOfR + 2, sizeOfS + sizeOfR + 2))

            return XyoRAndS(r, s)
        }

        override fun getInstance(byteArray: ByteArray): XyoEcdsaSignature {
            val rAndS = getRAndS(XyoObjectStructure(byteArray, 0).valueCopy)
            return XyoEcdsaSignature(rAndS.r, rAndS.s, byteArray)
        }
    }
}

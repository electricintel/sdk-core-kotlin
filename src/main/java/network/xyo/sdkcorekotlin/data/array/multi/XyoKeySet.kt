package network.xyo.sdkcorekotlin.data.array.multi

import network.xyo.sdkcorekotlin.XyoResult
import network.xyo.sdkcorekotlin.data.XyoObject
import network.xyo.sdkcorekotlin.data.array.XyoArrayDecoder
import java.nio.ByteBuffer

open class XyoKeySet(override var array: Array<XyoObject>) : XyoMultiTypeArrayBase() {
    override val id: XyoResult<ByteArray> = XyoResult(byteArrayOf(major, minor))
    override val sizeIdentifierSize: XyoResult<Int?> = sizeOfBytesToGetSize

    companion object : XyoArrayProvider() {
        override val major: Byte = 0x02
        override val minor: Byte = 0x02
        override val sizeOfBytesToGetSize: XyoResult<Int?> = XyoResult(2)

        override fun readSize(byteArray: ByteArray): XyoResult<Int> {
            return XyoResult(ByteBuffer.wrap(byteArray).short.toInt())
        }

        override fun createFromPacked(byteArray: ByteArray): XyoResult<XyoObject> {
            val unpackedArray = XyoArrayDecoder(byteArray, false, 2)
            val unpackedArrayObject = XyoKeySet(unpackedArray.array.toTypedArray())
            return XyoResult(unpackedArrayObject)
        }
    }
}
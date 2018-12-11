package network.xyo.sdkcorekotlin.boundWitness

import kotlinx.coroutines.runBlocking
import network.xyo.sdkcorekotlin.XyoTestBase
import network.xyo.sdkcorekotlin.hashing.basic.XyoBasicHashBase
import network.xyo.sdkcorekotlin.schemas.XyoSchemas
import org.junit.Assert
import org.junit.Test
import java.math.BigInteger

class XyoBoundWitnessInterpretTest : XyoTestBase() {

    @Test
    fun testInterpreterBoundWitness () {
        val boundWitnessBytes = "6002012B201547201944000C4192BAF8FBA41F6B5CA997DF7634F1F33176E0DDA8F7B485C6CD2EBC3BA06D4EEC8BB98284DB33761BA8A7668D1A5C140384968A0BE3436067F10A0D6B7F5AAFFF201547201944000C41ED1512DA596726D8E19A592BBA5573D31174C424FDFD7A0D14B3088BD22F0EB520F99E19D78DBBD613B79277FEB2BD0911C4C379E69B8688CC744B5B5ACF928F20174A201A470009442100CAC1C5F12BCCEA80C176FCCEEFEC616E86A9F208F43E45D49E8F32F76278B9F8202ABFC11D935F56D5CFECFDC66D4CA37D67C69AE6CD3C1DB41794C3C7FF41FE90201749201A4600094320656984EF23EAD4304E4A1AB3321F64BF9629FFE0E3A4097B181C2295892578D2205B90DAD8607D3BE600209771E2A19EC9EA3BB7BEE9D44A99395E85577FBCDBB7".hexStringToByteArray()
        val expectedFetterOne = "201547201944000C4192BAF8FBA41F6B5CA997DF7634F1F33176E0DDA8F7B485C6CD2EBC3BA06D4EEC8BB98284DB33761BA8A7668D1A5C140384968A0BE3436067F10A0D6B7F5AAFFF"                .hexStringToByteArray()
        val expectedWitnessOne = "201749201A4600094320656984EF23EAD4304E4A1AB3321F64BF9629FFE0E3A4097B181C2295892578D2205B90DAD8607D3BE600209771E2A19EC9EA3BB7BEE9D44A99395E85577FBCDBB7"           .hexStringToByteArray()
        val expectedFetterTwo = "201547201944000C41ED1512DA596726D8E19A592BBA5573D31174C424FDFD7A0D14B3088BD22F0EB520F99E19D78DBBD613B79277FEB2BD0911C4C379E69B8688CC744B5B5ACF928F"                .hexStringToByteArray()
        val expectedWitnessTwo = "20174A201A470009442100CAC1C5F12BCCEA80C176FCCEEFEC616E86A9F208F43E45D49E8F32F76278B9F8202ABFC11D935F56D5CFECFDC66D4CA37D67C69AE6CD3C1DB41794C3C7FF41FE90"       .hexStringToByteArray()
        val createdBoundWitness = XyoBoundWitness.getInstance(boundWitnessBytes)

        Assert.assertEquals(2, createdBoundWitness[XyoSchemas.FETTER.id].size)
        Assert.assertEquals(2, createdBoundWitness[XyoSchemas.WITNESS.id].size)
        Assert.assertEquals(2, createdBoundWitness.numberOfParties)

        Assert.assertArrayEquals(expectedFetterOne, createdBoundWitness.getFetterOfParty(0)?.bytesCopy)
        Assert.assertArrayEquals(expectedWitnessOne, createdBoundWitness.getWitnessOfParty(0)?.bytesCopy)
        Assert.assertArrayEquals(expectedFetterTwo, createdBoundWitness.getFetterOfParty(1)?.bytesCopy)
        Assert.assertArrayEquals(expectedWitnessTwo, createdBoundWitness.getWitnessOfParty(1)?.bytesCopy)
    }

    @Test
    fun testGetSigningData () {
        val boundWitnessBytes = "6002012B201547201944000C4192BAF8FBA41F6B5CA997DF7634F1F33176E0DDA8F7B485C6CD2EBC3BA06D4EEC8BB98284DB33761BA8A7668D1A5C140384968A0BE3436067F10A0D6B7F5AAFFF201547201944000C41ED1512DA596726D8E19A592BBA5573D31174C424FDFD7A0D14B3088BD22F0EB520F99E19D78DBBD613B79277FEB2BD0911C4C379E69B8688CC744B5B5ACF928F20174A201A470009442100CAC1C5F12BCCEA80C176FCCEEFEC616E86A9F208F43E45D49E8F32F76278B9F8202ABFC11D935F56D5CFECFDC66D4CA37D67C69AE6CD3C1DB41794C3C7FF41FE90201749201A4600094320656984EF23EAD4304E4A1AB3321F64BF9629FFE0E3A4097B181C2295892578D2205B90DAD8607D3BE600209771E2A19EC9EA3BB7BEE9D44A99395E85577FBCDBB7".hexStringToByteArray()
        val expectedSigningBytes = "201547201944000C4192BAF8FBA41F6B5CA997DF7634F1F33176E0DDA8F7B485C6CD2EBC3BA06D4EEC8BB98284DB33761BA8A7668D1A5C140384968A0BE3436067F10A0D6B7F5AAFFF201547201944000C41ED1512DA596726D8E19A592BBA5573D31174C424FDFD7A0D14B3088BD22F0EB520F99E19D78DBBD613B79277FEB2BD0911C4C379E69B8688CC744B5B5ACF928F".hexStringToByteArray()
        val createdBoundWitness = XyoBoundWitness.getInstance(boundWitnessBytes)

        Assert.assertArrayEquals(expectedSigningBytes, createdBoundWitness.signingData)
    }


    @Test
    fun testBoundWitnessHash () {
        runBlocking {
            val boundWitnessHash = "00102123B04C77DD8DBFFEF14251293DA9FF845C2294BCA5F4F56469C22D2E4EFDE49C".hexStringToByteArray()
            val boundWitnessBytes = "6002012B201547201944000C4192BAF8FBA41F6B5CA997DF7634F1F33176E0DDA8F7B485C6CD2EBC3BA06D4EEC8BB98284DB33761BA8A7668D1A5C140384968A0BE3436067F10A0D6B7F5AAFFF201547201944000C41ED1512DA596726D8E19A592BBA5573D31174C424FDFD7A0D14B3088BD22F0EB520F99E19D78DBBD613B79277FEB2BD0911C4C379E69B8688CC744B5B5ACF928F20174A201A470009442100CAC1C5F12BCCEA80C176FCCEEFEC616E86A9F208F43E45D49E8F32F76278B9F8202ABFC11D935F56D5CFECFDC66D4CA37D67C69AE6CD3C1DB41794C3C7FF41FE90201749201A4600094320656984EF23EAD4304E4A1AB3321F64BF9629FFE0E3A4097B181C2295892578D2205B90DAD8607D3BE600209771E2A19EC9EA3BB7BEE9D44A99395E85577FBCDBB7".hexStringToByteArray()
            val createdBoundWitness = XyoBoundWitness.getInstance(boundWitnessBytes)
            val hashProvider = XyoBasicHashBase.createHashType(XyoSchemas.SHA_256, "SHA-256")

            val hash = createdBoundWitness.getHash(hashProvider).await().bytesCopy
            Assert.assertArrayEquals(boundWitnessHash, hash)
        }
    }
}
package network.xyo.sdkcorekotlin.origin

import network.xyo.sdkcorekotlin.XyoTestBase
import network.xyo.sdkcorekotlin.boundWitness.XyoBoundWitness
import org.junit.Assert
import org.junit.Test

class XyoOriginBoundWitnessUtilTest : XyoTestBase() {

    @Test
    fun testGetBridgeBlocks () {
        val boundWitnessBytes = "600201A52015CB2019C8000C419837C629D6793E9367D8EB680B8960C3A2D30A857FF5E632F0EF6E411348946519621901C68A95DA9E104A34EE8D0F1DD02DD8581FFE9E5704688569EC992851000D820098024DDCF49F292CAA547A4F56940BED71F6F1D4604624A16B1FC265ADA0D63C93A502DE09C77BDE374CA7189ADAF11219850A936BE4B0BFB83040916C6AD521726A7BBAD06F35B4B997F89F1046245E69D6760D92EE4EAE736F706B164DD600DC68ACF4227F41DAD264A9C07CC39ADF84EA8900B0BAE5DB59668DD4F3380B472017D4201ACA0009442021772D688720F35685694BD78CD0DBAA7762A93B8A704655D3F77D25C591126E2100C3BCEC4EC4715FA8A0CF38F9C78C568E519E6DAC97E2CFCB29405A4A8E0D80AF000A816FCBDE9C69E9CC106345F3A42B3A669FD9ADF024E12FD30FF6C4AD76ED82268E18EF2685D2A0B52435DD59414DB202758CEC4367098A15261BD2C94342C3214D7E2D862712E633C20D9EC89B5469B1DD390511A674959A122D74A745443B4891918B1003F143592404957C394FE8082BC2820B08656BC2E09CECBAF1E43B01F330050520020200".hexStringToByteArray()
        val createdBoundWitness = XyoBoundWitness.getInstance(boundWitnessBytes)
        val bridgedBlocks = XyoOriginBoundWitnessUtil.getBridgedBlocks(createdBoundWitness)

        Assert.assertTrue(bridgedBlocks?.hasNext() ?: true)
        Assert.assertArrayEquals(bridgedBlocks?.next()?.valueCopy, byteArrayOf(0x00))
        Assert.assertFalse(bridgedBlocks?.hasNext() ?: true)
    }

    @Test
    fun testGetBridgeBlocksWithNone () {
        val boundWitnessBytes = "600201A22015CB2019C8000C41170F9302323929FD3FD8A72851F73866A0BFC6D488040E9D921689E01B9E25E4393B0984576763DD9C5DA95E609A80B4CC12064758C1AEEE28AE264015BF474F000D8200AEB335766EC511499DDE566579B4ED1562079AA543388B2EDED68ED68363AE9DAE25E7E29B9A5607E676A5F50CC6EB5CBCEBDEE30FB3F1CB9DA0074D4D3CA29B8BFD42AEEE44CA7C26134F4401FF67332C549AD72B36FBF9211D07B0B825C137D6A0DD13EE35FE446B55D22E66CE751216DC4BB823A3A62C3D0208CAC0DF68AB2017D1201ACA00094421009A0FF234B98891EE3FF99365A3CA6AB804173F1A8619934134A68F59FBDCA92E200C04A196D4A39C987C984E18B79D3EE81667DD92E962E6C630DB5D7BDCDB1988000A81713AB83E5D8B4EF6D2EAB4D70B61AADCA01E733CB0B3D072DE307CDBCD09F46D528A7159EB73DEBB018871E30D182F5BBB426689E758A7BFD4C51D0AD116CA621BF1C39DA49A837D525905D22BAB7C1874F6C7E6B4D56139A15C3BE1D1DC8E061C241C060A24B588217E37D6206AFE5D71F4698D42E25C4FCE996EECCF7690B900130200".hexStringToByteArray()
        val createdBoundWitness = XyoBoundWitness.getInstance(boundWitnessBytes)
        val bridgedBlocks = XyoOriginBoundWitnessUtil.getBridgedBlocks(createdBoundWitness)

        Assert.assertEquals(null, bridgedBlocks)
    }
}
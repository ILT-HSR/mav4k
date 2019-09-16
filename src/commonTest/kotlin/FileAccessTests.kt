import ch.hsr.ilt.mav4k.Path
import ch.hsr.ilt.mav4k.workingDirectory
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FileAccessTests {

    @Test
    fun `Known-good path exists`() {
        val commonXml = workingDirectory + "third_party/mavlink/message_definitions/v1.0/common.xml"
        assertTrue(commonXml.exists)
    }

    @Test
    fun `Unlikely path does not exist`() {
        val unlikely = Path("WE_HOPE_THAT_NO_ONE_EVER_CREATES_THIS_FILE")
        assertFalse{unlikely.exists}
    }

}
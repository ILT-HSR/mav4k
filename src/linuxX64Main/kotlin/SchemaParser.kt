package ch.hsr.ilt.mav4k

import interop.*

actual class SchemaParser actual constructor() {

    actual fun parse(file: Path) {
        println(file)
        xmlReaderForFile(file.toString(), null, 0)?.let { reader ->
            var status = xmlTextReaderRead(reader)
            while(status == 1) {
                processNode(reader)
                status = xmlTextReaderRead(reader)
            }
            xmlFreeTextReader(reader)
            if (status != 0) {
                TODO("Handle parse errors")
            }
        } ?: TODO("Handle file not found")
    }

    private fun processNode(reader: xmlTextReaderPtr) {
        val type = xmlTextReaderNodeType(reader)
        println("type: $type")
    }

}
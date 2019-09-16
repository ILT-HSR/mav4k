package ch.hsr.ilt.mav4k

expect class SchemaParser() {
    fun parse(file: Path)
}

class Schema
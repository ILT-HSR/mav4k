package ch.hsr.ilt.mav4k

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

actual val workingDirectory: Path get() = Path(System.getProperty("user.dir"))

actual class NativePath actual constructor(actual val raw: String) {

    actual companion object {
        actual val SEPARATOR: String = File.separator
    }

    actual fun exists() = Files.exists(Paths.get(raw))

    actual fun join(other: NativePath) = NativePath(Paths.get(raw, other.raw).toString())

}
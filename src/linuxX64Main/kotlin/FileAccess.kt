package ch.hsr.ilt.mav4k

import kotlinx.cinterop.*
import platform.posix.*

actual val workingDirectory: Path
    get() {
        var bufferSize = 512
        var cwd: Either<Int, String> = fail(ERANGE)
        while (cwd is Left<Int> && cwd.value == ERANGE) {
            cwd = getcwd(bufferSize)
            bufferSize *= 2
        }
        return Path(
            when (cwd) {
                is Left<Int> -> ""
                is Right<String> -> cwd.value
            }
        )
    }

actual class NativePath actual constructor(actual val raw: String) {

    actual companion object {
        actual val SEPARATOR = "/"
    }

    actual fun exists() = memScoped {
        val info = alloc<stat>()
        val result = stat(raw, info.ptr)
        result == 0
    }

    actual fun join(other: NativePath) = NativePath("$raw$SEPARATOR${other.raw}")

}

private fun getcwd(bufferSize: Int) =
    ByteArray(bufferSize).usePinned {
        set_posix_errno(0)
        getcwd(it.addressOf(0), it.get().size.convert())
        when {
            errno != 0 -> fail(errno)
            else -> pure(it.get().toKString())
        }
    }

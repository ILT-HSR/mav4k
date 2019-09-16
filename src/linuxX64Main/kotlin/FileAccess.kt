package ch.hsr.ilt.mav4k

import kotlinx.cinterop.addressOf
import kotlinx.cinterop.convert
import kotlinx.cinterop.toKString
import kotlinx.cinterop.usePinned
import platform.posix.ERANGE
import platform.posix.errno
import platform.posix.getcwd
import platform.posix.set_posix_errno

private fun getcwd(bufferSize: Int) =
    ByteArray(bufferSize).usePinned {
        set_posix_errno(0)
        getcwd(it.addressOf(0), it.get().size.convert())
        when {
            errno != 0 -> fail(errno)
            else -> pure(it.get().toKString())
        }
    }

actual val workingDirectory: String
    get() {
        var bufferSize = 512
        var cwd: Either<Int, String> = fail(ERANGE)
        while (cwd is Left<Int> && cwd.value == ERANGE) {
            cwd = getcwd(bufferSize)
            bufferSize *= 2
        }
        return when (cwd) {
            is Left<Int> -> ""
            is Right<String> -> cwd.value
        }
    }
package ch.hsr.ilt.mav4k

class Path(raw: String = "") {

    companion object {
        val SEPARATOR: String get() = NativePath.SEPARATOR
    }

    private constructor(native: NativePath) : this(native.raw)

    private val fNativePath = NativePath(raw)

    fun join(other: Path): Path = Path(fNativePath.join(other.fNativePath))

    fun join(other: String): Path = join(Path(other))

    operator fun plus(other: Path): Path = join(other)

    operator fun plus(other: String): Path = join(other)

    val exists: Boolean get() = fNativePath.exists()

    override fun toString(): String = fNativePath.raw

}

expect class NativePath(raw: String) {

    companion object {
        val SEPARATOR: String
    }

    val raw: String

    fun exists(): Boolean

    fun join(other: NativePath): NativePath

}

expect val workingDirectory: Path
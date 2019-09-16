import ch.hsr.ilt.mav4k.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EitherTests {

    @Test
    fun `Monadic bind returns the object unmodified if its argument is a 'Left'`() {
        val either: Either<Int, String> = fail(42)
        val result = bind(either) {
            pure('a')
        }
        assertTrue(result is Left<Int>)
        assertEquals(either, result)
    }

    @Test
    fun `Monadic bind returns the new value it its argument is a 'Right'`() {
        val either: Either<Boolean, Char> = pure('a')
        val result = bind(either) {
            pure(42)
        }
        assertTrue(result is Right<Int>)
        assertEquals(42, result.value)
    }

    @Test
    fun `Functor bind returns the object unmodified if its argument is a 'Left'`() {
        val either: Either<String, Char> = fail("Ada Lovelace")
        val result = fmap(either) {
            42
        }
        assertTrue(result is Left<String>)
        assertEquals(either, result)
    }

    @Test
    fun `Functor bind return the newly wrapped object it its argument is a 'Right'`() {
        val either: Either<Int, Boolean> = pure(true)
        val result = fmap(either) {
            "Grace Hopper"
        }
        assertTrue(result is Right<String>)
        assertEquals("Grace Hopper", result.value)
    }
}
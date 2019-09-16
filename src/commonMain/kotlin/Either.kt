package ch.hsr.ilt.mav4k

sealed class Either<out LeftT, out RightT>

class Left<out LeftT>(val value: LeftT) : Either<LeftT, Nothing>()

class Right<out RightT>(val value: RightT) : Either<Nothing, RightT>()

/**
 * Convenience function to create a new `Left`
 */
fun <LeftT> fail(value: LeftT) = Left(value)

/**
 * Convenience function to create a new `Right`
 */
fun <RightT> pure(value: RightT) = Right(value)

/**
 * Monadic bind
 */
fun <LeftT, RightT, ResT> bind(either: Either<LeftT, RightT>, fn: (RightT) -> Either<LeftT, ResT>) = when (either) {
    is Left -> either
    is Right -> fn(either.value)
}

/**
 * Functor map
 */
fun <LeftT, RightT, ResT> fmap(either: Either<LeftT, RightT>, fn: (RightT) -> ResT) = when (either) {
    is Left -> either
    is Right -> Right(fn(either.value))
}
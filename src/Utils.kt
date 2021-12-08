import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 *
 * @param name will be appended with `.txt`
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()
    .also {
        if (it.isEmpty()) {
            println("You didn't add the input!")
        }
    }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Convert a [List] of [String]s to [Int]s.
 *
 * Explodes if any of the strings cannot be converted to an integer.
 */
@Throws(NumberFormatException::class)
fun List<String>.toIntList(): List<Int> = this.map(String::toInt)

/**
 * Split this [String] on the last occurrence of [delimiter].
 */
fun String.splitLast(delimiter: String = " "): Pair<String, String> =
    lastIndexOf(delimiter).let { index ->
        substring(0, index) to substring(index + delimiter.length)
    }

class InvalidInputException(message: String? = null) : Exception(message)

@Suppress("FunctionName", "unused")
fun Any?.TODO(): Nothing = throw NotImplementedError()
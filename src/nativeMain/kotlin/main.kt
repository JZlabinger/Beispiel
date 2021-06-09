/**
 * Digits written in english in ascending order.
 */
private val digits = listOf(
    "zero",
    "one",
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine"
)

/**
 * Takes a string of written digits and returns a String with the equation of the digits sum.
 * The digits have to be separated by a given separator. If a written digit cannot be mapped
 * to an integer, because it is not in the 'digits' list, it will be ignored. Case of digits
 * does not matter.
 *
 * Example: extractSumFromString("zero-ONE-fault-thRee", "-") will return: "0+1+3=4"
 *
 * @param input: the string of written digits.
 * @param separator: the separator used in the input. Defaults to "-"
 *
 * @return an equation of the sum of the given digits
 */
fun extractSumFromString(input: String, separator: String = "-"): String {
    val integers = input
        .split(separator)
        .map { digits.indexOf(it.toLowerCase()) }
        .filterNot { it == -1 }
    return integers.joinToString(separator = "+", postfix = "=${integers.sum()}")
}

fun main() {
    test("zero-ONE-fault-thRee", "0+1+3=4", "-")
    test("zero-ONE-fault-thRee", "0+1+3=4")
    test("zero-ONE-fault-thRee", "=0", "+")
    test("nine&eight&seven&six", "9+8+7+6=30", "&")
    test("FIVEPLUSTWOPLUSFOUR", "5+2+4=11", "PLUS")
    test("one", "1=1", "Doesn't matter")
    test("nIne-oNE-ZEro-Eight-oNe-one-SeVeN-TWO-SiX-tHrEE-random-fiVe-fOUr-ONE",
        "9+1+0+8+1+1+7+2+6+3+5+4+1=48")
    test("", "=0")
}

/**
 * Helper function to test extractSumFromString()
 */
private fun test(input: String, expected: String, separator: String?=null) {
    val result = if (separator == null) extractSumFromString(input) else extractSumFromString(input, separator)
    println("$input <- Input\n$separator <- Separator\n$result <- Result\n$expected <- Expected\n")
    assert(result == expected)
}
fun main() {

    fun part1(input: List<String>): Int = input
        .map { it.toCharArray().toList() }
        .fold(
            input.first().toCharArray().map { 0 to 0 }
        ) { acc, bits ->
            acc.zip(bits) { bitCounts, bit ->
                if (bit == '0') {
                    bitCounts.copy(first = bitCounts.first + 1)
                } else {
                    bitCounts.copy(second = bitCounts.second + 1)
                }
            }
        }
        .joinToString("") { if (it.first > it.second) "0" else "1" }
        .let { bitString ->
            val gamma = bitString.toInt(2)
            val epsilon = bitString.map { if (it == '0') '1' else '0' }.joinToString("").toInt(2)
            gamma * epsilon
        }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    // check(part2(testInput) == 1)

    val input = readInput("Day03")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

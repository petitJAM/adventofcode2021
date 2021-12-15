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
        val oxygenGeneratorRating = input.first().indices
            .fold(input) { acc, idx ->
                if (acc.size == 1) return@fold acc
                val bitCriteria = acc.groupingBy { it[idx] }
                    .eachCount()
                    .let {
                        if (it.getValue('0') > it.getValue('1')) '0' else '1'
                    }
                acc.filter { it[idx] == bitCriteria }
            }
            .first()
            .toInt(2)

        val co2ScrubberRating = input.first().indices
            .fold(input) { acc, idx ->
                if (acc.size == 1) return@fold acc
                val bitCriteria = acc.groupingBy { it[idx] }
                    .eachCount()
                    .let {
                        if (it.getValue('0') > it.getValue('1')) '1' else '0'
                    }
                acc.filter { it[idx] == bitCriteria }
            }
            .first()
            .toInt(2)

        return oxygenGeneratorRating * co2ScrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun main() {
    fun List<Int>.countIncreasing(): Int = this.zipWithNext().count { it.first < it.second }

    fun part1(input: List<String>): Int {
        return input.toIntList().countIncreasing()
    }

    fun part2(input: List<String>): Int {
        return input.toIntList()
            .windowed(3) { it.sum() }
            .countIncreasing()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

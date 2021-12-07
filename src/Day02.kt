fun main() {

    fun part1(input: List<String>): Int {
        return input.map(String::splitLast)
            .map { it.first to it.second.toInt() }
            .fold(Coords(0, 0)) { acc, pair ->
                val direction = pair.first
                val distance = pair.second
                when (direction) {
                    "forward" -> {
                        Coords(acc.horizontal + distance, acc.depth)
                    }
                    "down" -> {
                        Coords(acc.horizontal, acc.depth + distance)
                    }
                    "up" -> {
                        Coords(acc.horizontal, acc.depth - distance)
                    }
                    else -> {
                        throw InvalidInputException("Unknown input $pair")
                    }
                }
            }
            .let { it.horizontal * it.depth }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)

    val input = readInput("Day02")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

typealias Coords = Pair<Int, Int>

val Coords.horizontal: Int
    get() = this.first

val Coords.depth: Int
    get() = this.second
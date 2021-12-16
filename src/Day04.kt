fun main() {

    fun part1(input: List<String>): Int {
        val (drawnNumbers, bingoBoards) = parseInput(input)

        var workingBoards = bingoBoards
        var winningBoard: BingoBoard? = null
        var winningNumber: Int? = null

        for (number in drawnNumbers) {
            workingBoards = workingBoards.map { it.mark(number) }

            val winner = workingBoards.firstOrNull { it.isWinning() }
            if (winner != null) {
                winningBoard = winner
                winningNumber = number
                break
            }
        }

        check(winningBoard != null) { "No winning board?" }
        check(winningNumber != null) { "No winning number?" }

        val unmarkedSum = winningBoard.sumUnmarkedNumbers()

        return winningNumber * unmarkedSum
    }

    fun part2(input: List<String>): Int {
        val (drawnNumbers, bingoBoards) = parseInput(input)

        var workingBoards = bingoBoards
        var lastWinningBoard: BingoBoard? = null
        var lastWinningNumber: Int? = null

        for (number in drawnNumbers) {
            workingBoards = workingBoards.map { it.mark(number) }

            if (workingBoards.size == 1) {
                lastWinningBoard = workingBoards.first()
                lastWinningNumber = number
                break
            }

            workingBoards = workingBoards.filterNot { it.isWinning() }
        }

        check(lastWinningBoard != null) { "No last winning board?" }
        check(lastWinningNumber != null) { "No last winning number?" }

        val unmarkedSum = lastWinningBoard.sumUnmarkedNumbers()

        return lastWinningNumber * unmarkedSum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun parseInput(input: List<String>): Pair<List<Int>, List<BingoBoard>> {
    val drawnNumbersList = input[0].split(',').map(String::toInt)
    val bingoBoards = input.subList(2, input.size)
        .joinToString("\n")
        .split("\n\n")
        .map { BingoBoard.fromInput(it) }
    return drawnNumbersList to bingoBoards
}

data class BingoBoard(
    private val numbers: List<Int>,
    private val markedNumbers: List<Int> = emptyList(),
) {
    companion object {
        fun fromInput(input: String): BingoBoard {
            return BingoBoard(
                numbers = input.split(' ', '\n')
                    .filter(String::isNotEmpty)
                    .map(String::toInt)
            )
        }
    }

    fun mark(number: Int): BingoBoard {
        return this.copy(markedNumbers = markedNumbers + number)
    }

    fun isWinning(): Boolean {
        return winsWith(markedNumbers)
    }

    @Deprecated("Doesn't work")
    private fun winsWith(calledNumbers: List<Int>): Boolean {
        val lineRange = 0..4
        val rows = lineRange.map { x -> lineRange.map { y -> numbers[(x * 5) + y] } }
        val columns = lineRange.map { x -> lineRange.map { y -> numbers[x + (y * 5)] } }
//        val diagonals = listOf(
//            lineRange.map { i -> numbers[6 * i] },
//            lineRange.zip(lineRange.reversed()).map { (x, y) -> numbers[x + (y * 5)] }
//        )

        return rows.any { calledNumbers.containsAll(it) } ||
                columns.any { calledNumbers.containsAll(it) }
//                diagonals.any { calledNumbers.containsAll(it) }
    }

    fun sumUnmarkedNumbers(): Int {
        return (numbers - markedNumbers.toSet()).sum()
    }

    override fun toString() = buildString {
        append('\n')
        append("BingoBoard( ")
        append('\n')
        numbers.windowed(size = 5, step = 5).forEach { row ->
            append("  ")
            row.forEach {
                val formattedNumber = it.toString().padStart(2)
                if (it in markedNumbers) {
                    append("\u001B[32m$formattedNumber\u001B[0m")
                } else {
                    append(formattedNumber)
                }
                append(' ')
            }
            append('\n')
        }
        append(")")
    }
}
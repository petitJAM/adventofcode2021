fun main() {

    fun part1(input: List<String>): Int {
        return parseInput(input)
            .filter(Line::isStraightLine)
            .flatMap(Line::allPoints)
            .groupingBy { it }
            .eachCount()
            .filter { it.value >= 2 }
            .count()
    }

    fun part2(input: List<String>): Int {
        return parseInput(input)
            .flatMap(Line::allPoints)
            .groupingBy { it }
            .eachCount()
            .filter { it.value >= 2 }
            .count()
    }

    val line = Line(
        start = Point(1, 1),
        end = Point(3, 3),
    )

    println(line.allPoints)

    val line2 = Line(
        start = Point(9, 7),
        end = Point(7, 9),
    )

    println(line2.allPoints)

    println("--------")

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

private data class Point(val x: Int, val y: Int)

private data class Line(val start: Point, val end: Point) {

    val allPoints: List<Point>
        get() = if (start.x == end.x) {
            omniIntRange(start.y, end.y).map { Point(start.x, it) }
        } else if (start.y == end.y) {
            omniIntRange(start.x, end.x).map { Point(it, start.y) }
        } else {
            // Input is only ever 45 degrees, so this is sufficient.
            val rangeX = omniIntRange(start.x, end.x)
            val rangeY = omniIntRange(start.y, end.y)
            rangeX.zip(rangeY).map { (x, y) -> Point(x, y) }
        }

    val isStraightLine: Boolean
        get() = start.x == end.x || start.y == end.y
}

private fun parseInput(input: List<String>): List<Line> = input
    .map { it.splitLast(" -> ") }
    .map { pointStrings ->
        Line(
            parsePointString(pointStrings.first),
            parsePointString(pointStrings.second),
        )
    }

private fun parsePointString(pointString: String) = pointString
    .split(",")
    .map(String::toInt)
    .let {
        Point(it[0], it[1])
    }

private fun omniIntRange(start: Int, end: Int) = if (start < end) start..end else start downTo end
#!/bin/bash

DAY_NUM=$1

if  [[ ${#DAY_NUM} = 1 ]]; then
  DAY_NUM="0$DAY_NUM"
fi

echo "Creating day $DAY_NUM"

touch "src/Day$DAY_NUM.txt"
touch "src/Day${DAY_NUM}_test.txt"

KT_FILE="src/Day$DAY_NUM.kt"

if [[ ! -f "$KT_FILE" ]]; then
  cat <<KT >> $KT_FILE
fun main() {

    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${DAY_NUM}_test")
    check(part1(testInput) == 1)
    // check(part2(testInput) == 1)

    val input = readInput("Day$DAY_NUM")
    println("Part 1: \${part1(input)}")
    println("Part 2: \${part2(input)}")
}
KT
fi

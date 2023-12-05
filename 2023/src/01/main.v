module main

import os { read_lines }

fn main() {
	lines := read_lines('./src/01/input.txt')!
	solve_part_1(lines)
	solve_part_2(lines)
}

fn solve_part_1(lines []string) {
	mut sum := 0

	digits := ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']

	for line in lines {
		found_numbers := line.split('').filter(digits.contains(it))

		if found_numbers.len == 1 {
			sum += (found_numbers[0].int() * 10 + found_numbers[0].int())
		}
		if found_numbers.len > 1 {
			sum += (found_numbers[0].int() * 10 + found_numbers[found_numbers.len - 1].int())
		}
	}
	println(sum)
}

struct Pair[T] {
	first  T
	second T
}

fn solve_part_2(lines []string) {
	mut sum := 0

	word_to_digit := {
		'zero':  0
		'one':   1
		'two':   2
		'three': 3
		'four':  4
		'five':  5
		'six':   6
		'seven': 7
		'eight': 8
		'nine':  9
	}

	for line in lines {
		mut digit_positions := []Pair[int]{}

		// Find all number words and their positions in the line
		for word, digit in word_to_digit {
			mut index := line.index(word) or { continue }
			for index >= 0 {
				digit_positions << Pair{
					first: index
					second: digit
				}
				index = line.index_after(word, index + 1)
			}
		}

		// Add all actual digits and their positions to the list
		for i, character in line {
			if character.is_digit() {
				digit_positions << Pair{
					first: i
					second: character - u8(`0`)
				}
			}
		}

		digit_positions.sort(a.first < b.first)

		if digit_positions.len >= 2 {
			first := digit_positions.first().second
			last := digit_positions.last().second
			sum += (first * 10 + last)
		} else if digit_positions.len == 1 {
			first := digit_positions.first().second
			sum += (first * 11)
		} else {
			println("Warning: '${line}' does not contain any numbers or number words")
		}
	}

	println(sum)
}

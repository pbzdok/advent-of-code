module day03

import os { read_lines }
import regex

pub fn run_task() ! {
	lines := read_lines('./src/day03/input.txt')!
	result1 := part1(lines)
	println(result1)
	result2 := part2(lines)
	println(result2)
}

fn part1(lines []string) int {
	input := lines.join_lines()
	pattern := r'mul\(\d+,\d+\)'
	mut re := regex.regex_opt(pattern) or { panic(err) }
	operations := re.find_all_str(input)

	mut sum_of_products := 0
	for op in operations {
		a, b := get_factors(op) or { continue }
		sum_of_products += a * b
	}

	return sum_of_products
}

fn part2(lines []string) int {
	input := lines.join_lines()
	pattern := r"(do\(\))|(don't\(\))|(mul\(\d+,\d+\))"
	mut re := regex.regex_opt(pattern) or { panic(err) }
	operations := re.find_all_str(input)

	mut is_enabled := true
	mut sum_of_products := 0
	for op in operations {
		match op {
			'do()' {
				is_enabled = true
				continue
			}
			"don't()" {
				is_enabled = false
				continue
			}
			else {}
		}
		if is_enabled {
			a, b := get_factors(op) or { continue }
			sum_of_products += a * b
		}
	}

	return sum_of_products
}

fn get_factors(op string) ?(int, int) {
	a, b := op.trim('mul()').split_once(',') or { return none }
	return a.int(), b.int()
}

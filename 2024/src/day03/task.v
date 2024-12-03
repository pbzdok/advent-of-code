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
	list := re.find_all_str(input)

	mut sum_of_products := 0
	for l in list {
		a, b := l.trim('mul()').split_once(',') or { continue }
		sum_of_products += a.int() * b.int()
	}

	return sum_of_products
}

fn part2(lines []string) int {
	input := lines.join_lines()
	pattern := r"(do\(\))|(don't\(\))|(mul\(\d+,\d+\))"
	mut re := regex.regex_opt(pattern) or { panic(err) }
	list := re.find_all_str(input)

	mut is_enabled := true
	mut sum_of_products := 0
	for l in list {
		match l {
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
			a, b := l.trim('mul()').split_once(',') or { continue }
			sum_of_products += a.int() * b.int()
		}
	}

	return sum_of_products
}

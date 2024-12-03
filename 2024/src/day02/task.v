module day02

import os { read_lines }
import math { abs, sign }

pub fn run_task() ! {
	lines := read_lines('./src/day02/input.txt')!
	result1 := part1(lines)
	println(result1)
	result2 := part2(lines)
	println(result2)
}

fn part1(lines []string) int {
	mut count := 0
	for line in lines {
		numbers := line.split(' ').map(it.int())
		mut derivative := []int{}
		for i in 0 .. numbers.len - 1 {
			d := numbers[i + 1] - numbers[i]
			derivative << d
		}

		is_in_range := derivative.all(abs(it) >= 1 && abs(it) <= 3)
		is_descending := derivative.all(sign(it) == -1)
		is_ascending := derivative.all(sign(it) == 1)

		if is_in_range && (is_descending || is_ascending) {
			count += 1
		}
	}

	return count
}

fn part2(lines []string) int {
    mut count := 0
	for line in lines {
		numbers := line.split(' ').map(it.int())
		mut differences := []int{}
		for i in 0 .. numbers.len - 1 {
			d := numbers[i + 1] - numbers[i]
			differences << d
		}
            
		mut is_valid := false
		for j in 0 .. numbers.len {
			mut new_numbers := []int{}
			for k in 0 .. numbers.len {
				if k != j {
					new_numbers << numbers[k]
				}
			}
			
			mut new_differences := []int{}
			for i in 0 .. new_numbers.len - 1 {
				d := new_numbers[i + 1] - new_numbers[i]
				new_differences << d
			}
    
			is_in_range := new_differences.all(abs(it) >= 1 && abs(it) <= 3)
			is_descending := new_differences.all(sign(it) == -1)
			is_ascending := new_differences.all(sign(it) == 1)
    
			if is_in_range && (is_descending || is_ascending) {
				is_valid = true
				break
			}
		}
    
		if is_valid {
			count += 1
		}
	}
    
	return count
}

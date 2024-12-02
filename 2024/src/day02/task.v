module day02

import os { read_lines }

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
		levels := line.split(' ').map(it.int())
		mut prev_level := levels[0]
		mut prev_diff := 0
		for level in levels {
			diff := level - prev_level
			
			prev_diff = diff
		}
	}

	return count
}

fn part2(lines []string) int {
	return 0
}

enum Direction {
	none
	up
	down
}

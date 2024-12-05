module day04

import os { read_lines }

pub fn run_task() ! {
	lines := read_lines('./src/day04/input.txt')!
	result1 := part1(lines)
	println(result1)
	result2 := part2(lines)
	println(result2)
}

fn part1(lines []string) int {
	mut grid := [][]rune{}
	for l in lines {
		grid << l.runes()
	}

	mut total := 0

	total += search_rows(pattern_1d, grid)
	total += search_rows(pattern_1d_reverse, grid)
	total += search_columns(pattern_1d, grid)
	total += search_columns(pattern_1d_reverse, grid)
	total += search_diagonals(pattern_2d, grid)
	total += search_diagonals(pattern_2d_reverse, grid)
	total += search_inverted_diagonals(pattern_2d, grid)
	total += search_inverted_diagonals(pattern_2d_reverse, grid)

	return total
}

fn part2(lines []string) int {
	return 0
}

fn search_rows(pattern []rune, grid [][]rune) int {
	p := pattern.len
	n := grid.len
	m := grid[0].len
	mut count := 0

	for i in 0 .. n {
		for j in 0 .. m - p + 1 {
			mut is_match := true
			for y in 0 .. p {
				if grid[i][j + y] != pattern[y] {
					is_match = false
					break
				}
			}
			if is_match {
				count += 1
			}
		}
	}

	return count
}

fn search_columns(pattern []rune, grid [][]rune) int {
	p := pattern.len
	n := grid.len
	m := grid[0].len
	mut count := 0

	for j in 0 .. m {
		for i in 0 .. n - p + 1 {
			mut is_match := true
			for x in 0 .. p {
				if grid[i + x][j] != pattern[x] {
					is_match = false
					break
				}
			}
			if is_match {
				count += 1
			}
		}
	}

	return count
}

fn search_diagonals(pattern [][]rune, grid [][]rune) int {
	p := pattern.len
	n := grid.len
	m := grid[0].len
	mut count := 0

	for i in 0 .. n - p + 1 {
		for j in 0 .. m - p + 1 {
			mut is_match := true
			for x in 0 .. p {
				if grid[i + x][j + x] != pattern[x][x] {
					is_match = false
					break
				}
			}
			if is_match {
				count += 1
			}
		}
	}

	return count
}

fn search_inverted_diagonals(pattern [][]rune, grid [][]rune) int {
	p := pattern.len
	n := grid.len
	m := grid[0].len
	mut count := 0

	mut i := n - 1
	for i >= 0 + p - 1 {
		for j in 0 .. m - p + 1 {
			mut is_match := true
			for x in 0 .. p {
				if grid[i - x][j + x] != pattern[x][x] {
					is_match = false
					break
				}
			}
			if is_match {
				count += 1
			}
		}
		i--
	}

	return count
}

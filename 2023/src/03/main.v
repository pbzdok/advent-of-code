module main

import os { read_lines }

fn main() {
	lines := read_lines('./src/03/input.txt')!
	solve_part_1(lines)
}

fn solve_part_1(lines []string) {
	mut matrix := [][]string{}

	digits := ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']

	symbols := [',', ';', ':', "'", '"', '[', ']', '{', '}', '(', ')', '?', '!', '@', '#', '$',
		'%', '^', '&', '*', '-', '_', '+', '=', '<', '>', '/', '\\', '|', '`', '~']

	neighborhood := [
		[-1, -1], [-1, 0], [-1, 1],
		[ 0, -1],          [ 0, 1],
		[ 1, -1], [ 1, 0], [ 1, 1],
	]

	for line in lines {
		matrix << line.split('')
	}

	mut sum := 0

	for row in 0 .. matrix.len {
		for col in 0 .. matrix[row].len {
			v := matrix[row][col]
			if v in symbols {
				print('\x1b[31m${v}\x1b[0m')

				for n in neighborhood {
					n_row := row + n[0]
					n_col := col + n[1]

					mut found := []string{}
					// Check the boundaries
					if n_row >= 0 && n_row < matrix.len && n_col >= 0 && n_col < matrix[0].len {

						if matrix[n_row][n_col] in digits {
							found << matrix[n_row][n_col]

							mut run_left := n_row
							for matrix[run_left][n_col] in digits {
								found.prepend(matrix[run_left][n_col])
								run_left--
							}

							mut run_right := n_row
							for matrix[run_right][n_col] in digits {
								found << matrix[run_right][n_col]
								run_right++
							}
						}
					}

					 sum += found.join('').int()
				}
			} else {
				print(v)
			}
		}
		println('')
	}
	println(sum)
}

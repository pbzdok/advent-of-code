module main

import os { read_lines }

fn main() {
	lines := read_lines('./src/02/input.txt')!
	solve_part_1(lines)
}

fn solve_part_1(lines []string) {
	available := [12, 13, 14] // r, g, b

	mut sum := 0

	for line in lines {
		colon_idx := line.index(':') or { panic(err) }
		game_id := line.substr(5, colon_idx).int()

		mut validities := []bool{}

		runs := line.substr(colon_idx + 1, line.len).split(';')
		for run in runs {
			mut rgb := [0, 0, 0] // r, g, b

			values := run.split(',')
			for value in values {
				n := value.split(' ')[1].trim(' ').int()

				if value.contains('red') {
					rgb[0] = rgb[0] + n
				} else if value.contains('green') {
					rgb[1] = rgb[1] + n
				} else if value.contains('blue') {
					rgb[2] = rgb[2] + n
				}
			}

			for i, v in rgb {
				if v <= available[i] {
					validities << true
				} else {
					validities << false
				}
			}
		}

		if validities.all(it == true) {
			sum += game_id
		}
	}

	println(sum)
}

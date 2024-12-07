module main

import benchmark
import src.day02
import src.day03
import src.day01
import src.day04
import src.day05

fn main() {
	println('Hello World! This is Advent of Code 2024!')

	println('Starting benchmark...')
	mut b := benchmark.start()

	day01.run_task()!
	b.measure('Day 01')

	day02.run_task()!
	b.measure('Day 02')

	day03.run_task()!
	b.measure('Day 03')

	day04.run_task()!
	b.measure('Day 04')

	day05.run_task()!
	b.measure('Day 05')

	b.stop()
}

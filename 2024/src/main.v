module main

import benchmark
import day01

fn main() {
	println('Hello World! This is Advent of Code 2024!')
	
	println('Starting benchmark...')
	mut b := benchmark.start()
	
	day01.run_task()!
	b.measure('Day 01')
}

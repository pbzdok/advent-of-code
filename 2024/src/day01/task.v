module day01

import os { read_lines }
import math { abs }

pub fn run_task() ! {
	lines := read_lines('./src/day01/input.txt')!
	result1 := part1(lines)
	println(result1)
	result2 := part2(lines)
	println(result2)
}

fn part1(lines []string) int {
	mut list_a := []int{}
	mut list_b := []int{}

	for line in lines {
		list_a << line.all_before(' ').int()
		list_b << line.all_after_last(' ').int()
	}
	assert list_a.len == list_b.len

	list_a.sort()
	list_b.sort()

	mut distance := 0
	for i in 0 .. list_a.len {
		distance += abs(list_a[i] - list_b[i])
	}

	return distance
}

fn part2(lines []string) int {
	mut list_a := []int{}
	mut list_b := []int{}

	for line in lines {
		list_a << line.all_before(' ').int()
		list_b << line.all_after_last(' ').int()
	}
	assert list_a.len == list_b.len

	mut similarity_score := 0
	for i in 0 .. list_a.len {
		x := list_a[i]
		n := list_b.filter(it == x).len
		similarity_score += x * n
	}

	return similarity_score
}

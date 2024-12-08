module day05

import os { read_lines }

pub fn run_task() ! {
	lines := read_lines('./src/day05/input.txt')!
	result1 := part1(lines)
	println(result1)
	result2 := part2(lines)
	println(result2)
}

fn part1(lines []string) int {
	raw_rules := lines[0..lines.index('')]
	rules := parse_rules(raw_rules)
	raw_updates := lines[(lines.index('') + 1)..(lines.len)]
	all_updates := parse_updates(raw_updates)

	mut sorted_updates := [][]int{}
	for arr in all_updates {
		if is_sorted(rules, arr) {
			sorted_updates << arr
		}
	}

	mut sum := 0
	for arr in sorted_updates {
		sum += arr[arr.len / 2]
	}

	return sum
}

fn part2(lines []string) int {
	raw_rules := lines[0..lines.index('')]
	rules := parse_rules(raw_rules)
	raw_updates := lines[(lines.index('') + 1)..(lines.len)]
	all_updates := parse_updates(raw_updates)

	mut unsorted_updates := [][]int{}
	for arr in all_updates {
		if !is_sorted(rules, arr) {
			unsorted_updates << arr
		}
	}

	mut sorted_updates := [][]int{}
	for arr in unsorted_updates {
		mut sorted_arr := arr.sorted_with_compare(fn [rules] (a &int, b &int) int {
			return comparator(rules, *a, *b)
		})
		sorted_updates << sorted_arr
	}

	mut sum := 0
	for arr in sorted_updates {
		sum += arr[arr.len / 2]
	}

	return sum
}

fn parse_rules(raw_rules []string) map[int][]int {
	mut rules := map[int][]int{}
	for rr in raw_rules {
		ns, bs := rr.split_once('|') or { continue }
		n, b := ns.int(), bs.int()
		if n in rules {
			rules[n] << b
		} else {
			rules[n] = [b]
		}
	}
	return rules
}

fn parse_updates(raw_updates []string) [][]int {
	return raw_updates.map(it.split(',').map(it.int()))
}

fn is_sorted(rules map[int][]int, arr []int) bool {
	for i in 0 .. arr.len - 1 {
		if comparator(rules, arr[i], arr[i + 1]) > 0 {
			return false
		}
	}
	return true
}

fn comparator(rules map[int][]int, a int, b int) int {
	if a == b {
		return 0
	}
	if a in rules && b in rules[a] {
		return -1
	}
	if b in rules && a in rules[b] {
		return 1
	}
	return 0
}

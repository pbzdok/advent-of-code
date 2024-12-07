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
	println(rules)

	// unordered_updates := lines[lines.index('')..lines.len - 1]
	// mut ordered_updates := [][]int{}
	//
	// for uu in unordered_updates {
	// 	pages := uu.split(',').map(it.int())
	// 	for p in pages {
	//
	// 	}
	// }
	return 0
}

fn part2(lines []string) int {
	return 0
}

struct PageRule {
	n int
mut:
	before []int
}

fn parse_rules(raw_rules []string) []PageRule {
	mut rules := []PageRule{}
	for rr in raw_rules {
		ns, bs := rr.split_once('|') or { continue }
		n, b := ns.int(), bs.int()
		mut rule_exists := false
		for mut rule in rules {
			if rule.n == n {
				rule.before << b
				rule_exists = true
				break
			}
		}
		if !rule_exists {
			rules << PageRule{
				n:      n
				before: [b]
			}
		}
	}
	return rules
}

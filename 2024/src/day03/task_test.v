module day03

import regex

const test_input = [
	"xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))",
]

fn test_part1() {
	result := part1(test_input)
	assert result == 161
}

fn test_part2() {
	result := part2(test_input)
	assert result == 48
}

fn test_regex() {
	text := 'mul(2,4)'
	query := r'mul\(\d+,\d+\)'
	re := regex.regex_opt(query) or { panic(err) }
	println(text)
	println(re.get_query())
	println(re.matches_string(text))
}

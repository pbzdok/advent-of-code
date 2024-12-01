module day01

const test_input = [
	'3   4',
	'4   3',
	'2   5',
	'1   3',
	'3   9',
	'3   3',
]

fn test_part1() {
	result := part1(test_input)
	assert result == 11
}

fn test_part2() {
	result := part2(test_input)
	assert result == 31
}

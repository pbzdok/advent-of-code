module day02

const test_input = [
	'7 6 4 2 1',
	'1 2 7 8 9',
	'9 7 6 2 1',
	'1 3 2 4 5',
	'8 6 4 4 1',
	'1 3 6 7 9',
]

fn test_part1() {
	result := part1(test_input)
	assert result == 2
}

fn test_part2() {
	result := part2(test_input)
	assert result == 0
}

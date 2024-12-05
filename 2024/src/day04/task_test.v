module day04

const test_input = [
	'MMMSXXMASM',
	'MSAMXMSMSA',
	'AMXSXMAAMM',
	'MSAMASMSMX',
	'XMASAMXAMM',
	'XXAMMXXAMA',
	'SMSMSASXSS',
	'SAXAMASAAA',
	'MAMMMXMMMM',
	'MXMXAXMASX',
]

fn test_part1() {
	result := part1(test_input)
	assert result == 18
}

fn test_part2() {
	result := part2(test_input)
	assert result == 42
}

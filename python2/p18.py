# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=18
# Maximum path sum I

from timeit import default_timer as timer
t_start = timer()

triangle = """75
95 64
17 47 82
18 35 87 10
20 04 82 47 65
19 01 23 75 03 34
88 02 77 73 07 63 67
99 65 04 28 06 16 70 92
41 41 26 56 83 40 80 70 33
41 48 72 33 47 32 37 16 94 29
53 71 44 65 25 43 91 52 97 51 14
70 11 33 28 77 73 17 78 39 68 17 57
91 71 52 38 17 14 91 43 58 50 27 29 48
63 66 04 68 89 53 67 30 73 16 69 87 40 31
04 62 98 27 23 09 70 98 73 93 38 53 60 04 23"""

triangle = """3
7 4
2 4 6
8 5 9 3"""

rows = [map(int, row.split(' ')) for row in triangle.splitlines()]
num_rows = len(rows)
print rows
max_val = max(max(rows)) # highest single number, 9 for small, 99 for large triangle
max_sum = 0

row, col = 0,0
last_pos = (row, col)
while True:

    steps = []
    while row < num_rows:
        print "COL:%s, ROW:%s, VALUE: %d" % (col, row, rows[row][col])

        last_pos = (row, col)
        steps.append(last_pos)
        row += 1

    print "LAST", last_pos, steps
    cur_sum = 0
    for step in steps:
        row, col = step
        cur_sum += rows[row][col]


    break

    if col == num_rows and row == num_rows:
        break



print max_sum, timer() - t_start
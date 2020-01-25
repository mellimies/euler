# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=14
# Longest Collatz sequence

# Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.

from timeit import default_timer as timer
t_start = timer()

rv = None

num_start = 13
num_start = 999999

max_len = 0

for i in xrange(num_start, 0, -1):
    num = i
    len_sq = 0
    while num > 1:
        num = num * 3 + 1 if num % 2 else num / 2
        len_sq += 1

    if len_sq > max_len:
        max_len = len_sq
        rv = i

print rv, max_len, timer() - t_start
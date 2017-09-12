# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=52
# Permuted multiples

# Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.

from timeit import default_timer as timer

t_start = timer()

mults = range(1, 7)

rv = 0
i = 123455

while True:
    i += 1
    s = set(map(lambda x: ''.join(sorted(str(x * i))), mults))
    len_s = len(s)
    if len_s == 1:
        print "FOUND", i, s, map(lambda x: ''.join(str(x * i)), mults)
        rv = i
        break

print rv, timer() - t_start

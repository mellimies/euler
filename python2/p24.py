# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=24
# Lexicographic permutations

# What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?

from timeit import default_timer as timer

t_start = timer()
nums = range(10)

import itertools
l = sorted(itertools.permutations(nums))

rv = ''.join(map(str, (l[999999])))
print rv, timer() - t_start
# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=30
# Digit fifth powers

# Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.

from timeit import default_timer as timer

t_start = timer()

# 2**5 = 32
# 9**5 = 59049

exponent = 5
rv = 0

i = 11
while i < exponent * 9**exponent:
    nums = map(lambda x: int(x)**exponent, list(str(i)))
    if i == sum(nums):
        rv += i
        print i, nums
    i += 1

print rv, timer() - t_start
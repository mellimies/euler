# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=16
# Power digit sum

# 2**15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
# What is the sum of the digits of the number 2**1000?

from timeit import default_timer as timer
t_start = timer()

exponent = 1000

rv = reduce(lambda x, y: int(x) + int(y), list(str(2**exponent)))

print rv, timer() - t_start
# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=12
# Highly divisible triangular number

# What is the value of the first triangle number to have over five hundred divisors?

from timeit import default_timer as timer
from euler_tools import factors

t_start = timer()

rv = 0

i = 1
num = 0
while rv == 0:
    num += i
    i += 1
    if len(factors(num)) > 500:
        rv = num # 76576500
        break

print rv, timer() - t_start
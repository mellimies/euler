# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=48
# Self powers

# Find the last ten digits of the series, 1**1 + 2**2 + 3**3 + ... + 1000**1000.

from timeit import default_timer as timer
from euler_tools import is_prime, get_primes_up_to

t_start = timer()

limit = 1000
nums = range(1, limit + 1)
lsum = str(sum(map(lambda x: x**x, nums)))
print lsum

rv = lsum[-10:]
print rv, timer() - t_start

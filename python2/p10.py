# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=10
# Summation of primes

# The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
# Find the sum of all the primes below two million.

from timeit import default_timer as timer
t_start = timer()

from euler_tools import is_prime

#limit = 10
limit = 2000000

test_nums = xrange(5, limit, 2)

rv = sum([2,3])
rv += sum(filter(is_prime, test_nums))

print rv, timer() - t_start
# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=97
# Large non-Mersenne prime

# How many starting numbers below ten million will arrive at 89?

from timeit import default_timer as timer

t_start = timer()

n = 28433 * 2**7830457 + 1
rv = n % 10**10

print rv, timer() - t_start

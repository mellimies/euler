# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=41
# Pandigital prime

# What is the largest n-digit pandigital prime that exists?

from timeit import default_timer as timer
from euler_tools import is_prime, int2nums

t_start = timer()

n_digits = 8 # 8 had no solutions, did not wait for 9 to complete but submitted answer for 7 which was correct
n_start = sum(map(lambda x: x*10**(x-1), range(1, n_digits + 1)))

nums = xrange(n_start, 10**(n_digits-1)-2, -2)
p_digits = set(map(str, range(1, n_digits + 1)))

rv = None
limit = 10**(n_digits-1)
for p in nums:
    if p < limit:
        print "NO CAN DO", p
        break
    if is_prime(p):
        if p_digits == set(list(str(p))):
            rv = p
            break

print rv, timer() - t_start
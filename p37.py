# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=37
# Truncatable primes

# Find the sum of the only eleven primes that are both truncatable from left to right and right to left.

from timeit import default_timer as timer
from euler_tools import get_primes_up_to, is_prime

t_start = timer()

limit = 1000000
primes = []

primes = get_primes_up_to(limit)

rv = []
for p in primes:
    sp = str(p)
    vals = []
    for i, x in enumerate(list(sp)):
        vals.append(sp[i:]) # drop from left
        xx = sp[:-1*i] # drop from right
        if xx:
            vals.append(xx)
    rv.append(set(vals))

a = []

for r in sorted(rv):
    r = map(int, r)
    if all(map(lambda x: is_prime(x), r)):
        a.append(max(r))

rv = sum(a) - 2 - 3 - 5 - 7
print rv, timer() - t_start
# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=35
# Circular primes

# How many circular primes are there below one million?

from python2.euler_tools import is_prime
from timeit import default_timer as timer

t_start = timer()
limit = 10000

def int2nums(num):
    return map(int, list(str(num)))

nums = []
for n in xrange(3, limit, 2):
    if all(map(lambda x: x % 2, int2nums(n))): # drop any number that has even number anywhere
        nums.append(n)

primes = [2] + filter(is_prime, nums)
#print primes

import itertools
rv = []

for p in primes:
    ps = set(itertools.permutations(str(p)))
    x = map(lambda x: int(''.join(x)), ps)
    #x = map(lambda x: is_prime(int(''.join(x))), ps)
    print p, len(ps), map(lambda y: is_prime(y), x)
    x = map(lambda y: is_prime(y), x)
    if all(x):
        rv.append(p)

for i, r in enumerate(rv):
    print i, r
#print rv
print len(rv), timer() - t_start
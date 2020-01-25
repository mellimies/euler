# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=87
# Prime power triples

# How many numbers below fifty million can be expressed as the sum of a prime square, prime cube, and prime fourth power?

from timeit import default_timer as timer
from python2.euler_tools import get_primes_up_to

t_start = timer()

limit = 50000000

primes = get_primes_up_to(int(limit**0.5)) # for 50 M 7069

rv = set()

for x in primes:
    for y in primes:
        for z in primes:

            z4 = z**4
            if z4 >= limit:
                break

            y3 = y**3
            if z4+y3 >= limit:
                break

            s = sum([x**2, y3, z4])
            if s < limit:
                rv.add(s)

rv = len(rv)
print rv, timer() - t_start

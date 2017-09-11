# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=49
# Prime permutations

# What 12-digit number do you form by concatenating the three terms in this sequence?

from timeit import default_timer as timer
from euler_tools import is_prime, get_primes_up_to

t_start = timer()

primes = get_primes_up_to(9999, 999)

def test_nums(n1, n3):
    n2 = n1 + (n3 - n1) / 2

    if (n1 == n3):
        return False

    if not is_prime(n2):
        return False

    if (sorted(str(n1))) == (sorted(str(n2))) == (sorted(str(n3))): # anagrams
        return True

rv = []
try:
    for p1 in primes:
        for p3 in primes[::-1]:
            p2 = (p3 - p1) / 2 + p1
            if test_nums(p1, p3) and not sorted([p1, p2, p3]) in [sorted(r) for r in rv]:
                rv.append((p1, p2, p3))
                #print rv
                if len(rv) == 2:
                    raise
except:
    pass
rv = map(lambda x: ''.join([str(x) for x in x]), rv)
print rv, timer() - t_start

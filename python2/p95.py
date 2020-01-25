# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=95
# Amicable chains

# Find the smallest member of the longest amicable chain with no element exceeding one million

from timeit import default_timer as timer
import sympy
from python2.euler_tools import is_prime

t_start = timer()

cache = dict()
def proper_divisors(num, cache=cache):
    if num not in cache:
        d = sympy.ntheory.factorint(num)
        m = 1
        for k,v in d.items():
            m *= sum(map(lambda x: k**x, range(v+1)))
            #print k,v, map(lambda x: k**x, range(v+1))
        cache[num] = m-n

    return cache[num]

chains = dict()
invalid_numbers = set()

limit = 1000000

test_nums = range(3, limit/20) # can't find a good way to determine limit for start numbers
for n in test_nums:
    start_num = n
    current_chain = [start_num]
    while True:
        if n > limit or is_prime(n) or n in invalid_numbers:
            break

        n = proper_divisors(n)

        if n == start_num: # Amicable chain
            print "Y", n, current_chain
            chains[start_num] = current_chain
            break
        elif n in chains:
            invalid_numbers.add(start_num)
            break
        elif n in current_chain:
            invalid_numbers.add(start_num)
            break
        else:
            current_chain.append(n)

max_key, max_len = 0,0
for k,v in chains.items():
    l = len(v)
    if l > max_len:
        max_key = k
        max_len = l
        print k, l

print min(chains[max_key]), chains[max_key]

print timer() - t_start

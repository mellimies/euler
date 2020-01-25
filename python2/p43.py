# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=43
# Sub-string divisibility

# Find the sum of all 0 to 9 pandigital numbers with this property.

from timeit import default_timer as timer
from python2.euler_tools import is_prime

t_start = timer()

n_test = 1406357289

def is_pandigital(num):
    return len(set(list(str(num)))) == 10

#nums = [n_test]
nums = xrange(1234567890, 9876543211)
rv = []

indexes = range(1, 8)
print indexes

for n in nums:
    if not is_pandigital(n):
        continue

#    print n
    sn = str(n)
    nlist = map(lambda i: int(sn[i:i+3]), indexes)
    if all(map(lambda x: not is_prime(x), nlist)):
        print n
        rv.append(n)

print sum(rv), timer() - t_start

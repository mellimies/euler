# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=40
# Champernowne's constant

# If dn represents the nth digit of the fractional part, find the value of the following expression.
# d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000

from timeit import default_timer as timer
from euler_tools import get_primes_up_to, is_prime

t_start = timer()

limit = 1000000

s = ''
i = 0
while len(s) < limit:
    i += 1
    s += str(i)

rv = 1
i = 1
while i < limit + 1:
    rv *= int(s[i-1])
    i *= 10

print rv, timer() - t_start
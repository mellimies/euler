# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=57
# Square root convergents

# In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?

from timeit import default_timer as timer
from sympy import *

t_start = timer()

rv = 0
for i in range(1000):
    if i == 0:
        b = Rational(2)
    else:
        b = 2 + Rational(1/b)

    a = 1 + Rational(1/b)
    n, d = str(a).split("/")
    if len(n) > len(d):
        #print float(a), len(n), len(d)#, a
        rv += 1

print rv, timer() - t_start

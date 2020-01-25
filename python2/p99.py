# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=99
# Largest exponential


from timeit import default_timer as timer
import math

t_start = timer()

with open('p099_base_exp.txt') as f:
    lines = f.read().splitlines()


from itertools import compress
from operator import mul

def radix(b) :
    while b :
        yield b & 1
        b >>= 1

def squares(b) :
    while True :
        yield b
        b *= b

def fast_exp(b, exp) :
    return reduce(mul, compress(squares(b), radix(exp)), 1)

row, max_num = 0, 0

for i, l in enumerate(lines, 1):
    base, exponent = map(int, l.split(','))
    n = fast_exp(base,exponent)
    print i, base, exponent, n.bit_length()#, n



rv = 0
print rv, timer() - t_start

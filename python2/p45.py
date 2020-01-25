# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=45
# Triangular, pentagonal, and hexagonal

# Find the next triangle number from 40755 that is also pentagonal and hexagonal.

from timeit import default_timer as timer
import math

t_start = timer()

def h(n):
    return n * (2*n - 1)

def index_t(n):
    return (1 + math.sqrt((1+4*1*2*n)))/2

def is_t(n):
    return index_t(n) == int(index_t(n))

def index_p(n):
    return (1 + math.sqrt((1+4*3*2*n)))/6

def is_p(n):
    return index_p(n) == int(index_p(n))

def index_h(n):
    return (1 + math.sqrt((1+4*2*n)))/4

def is_h(n):
    return index_h(n) == int(index_h(n))

n_start = 40755
print is_h(n_start), index_h(n_start)

i_h = int(index_h(n_start))

while True:
    i_h += 1
    n = h(i_h)
    if is_p(n) and is_t(n):
        rv = n
        print n, index_h(n), index_p(n), index_t(n)
        break
rv = 0
print rv, timer() - t_start

# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=108
# Diophantine reciprocals I

# What is the least value of n for which the number of distinct solutions exceeds one-thousand?

from timeit import default_timer as timer
from sympy import Rational

t_start = timer()

n = 4
x = 6
y = 12
rv = 0

def test_num(n,y):
    rv = 1 / Rational( (1/Rational(n) - 1/Rational(y)) )
    return rv

print x, x == test_num(n, y)

print rv, timer() - t_start

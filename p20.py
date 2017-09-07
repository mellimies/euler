# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=20
# Factorial digit sum

# Find the sum of the digits in the number 100!

from timeit import default_timer as timer

t_start = timer()

rv = 1
num = 100

while num > 1:
    rv *= num
    num -= 1

rv = reduce(lambda x, y: int(x) + int(y), list(str(rv)))
print rv, timer() - t_start
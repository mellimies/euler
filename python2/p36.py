# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=36
# Double-base palindromes

# Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.

from timeit import default_timer as timer

t_start = timer()

def is_palindromic(x):
    y = str(x)
    return y == y[::-1]

limit = 1000000

nums = xrange(1, limit, 2)
nums = filter(is_palindromic, nums)
nums = filter(lambda x: is_palindromic(bin(x)[2:]), nums)

print nums, sum(nums)
rv = sum(nums)
print rv, timer() - t_start
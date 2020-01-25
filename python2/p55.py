# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=55
# Lychrel numbers

# How many Lychrel numbers are there below ten-thousand?

from timeit import default_timer as timer

t_start = timer()

def is_palindrome(num):
    s = str(num)
    return s == s[::-1]

def is_lychrel(num):

    for i in range(50):
        num += int(str(num)[::-1])

        if is_palindrome(num):
            return False

    return True

rv = filter(lambda x: is_lychrel(x), range(10000))

print len(rv), timer() - t_start

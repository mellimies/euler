# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=25
# 1000-digit Fibonacci number

# What is the index of the first term in the Fibonacci sequence to contain 1000 digits?

from timeit import default_timer as timer

t_start = timer()

low, high = 1, 1
end = False
num_digits = 1000
rv = 1

while True:
    rv += 1
#    print "*", low
    low, high = high, low + high

    if len(str(low)) == num_digits:
        break

print rv, timer() - t_start
# https://projecteuler.net/problem=1
# Multiples of 3 and 5

from timeit import default_timer as timer

t_start = timer()

limit = 1000
numbers = range(limit)

rv = sum(filter(lambda x: x % 3 == 0 or x % 5 == 0, numbers))  # 233168
print rv
print rv, timer() - t_start

# https://projecteuler.net/problem=5
# Smallest multiple

# 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
# What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

from timeit import default_timer as timer
t_start = timer()

limit = 20
test_nums = xrange(1, limit + 1)

rv = 1
while True:
    i = rv * limit * (limit - 1)
    if sum(map(lambda x: i % x, test_nums)) == 0:
        rv = i
        break

    rv += 1

print rv, timer() - t_start
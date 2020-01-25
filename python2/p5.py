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

print "Solution B"

t_start = timer()

import euler_tools

primes = euler_tools.get_primes_up_to(limit)

num_to_test = reduce(lambda x, y: x * y, primes)

if limit != primes[-1]:  # upper limit is not price -> multiply with all numbers that are after last prime
    diff = limit - primes[-1]
    num_to_test = num_to_test * limit
#print primes, diff, num_to_test

while True:
    #print num_to_test, sum(map(lambda x: num_to_test % x, test_nums))
    if sum(map(lambda x: num_to_test % x, test_nums)) == 0:
        rv_b = num_to_test
        print "found B", rv_b
        break

    num_to_test += 2
    #if num_to_test > 3000:
    #    break


print rv_b, timer() - t_start

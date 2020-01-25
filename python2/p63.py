# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=63
# Powerful digit counts

# How many n-digit positive integers exist which are also an nth power?

# Can't find a proper way to end iteration, just checked result and it was correct after a couple attempts :(

from timeit import default_timer as timer

t_start = timer()

plimit = 30
rv = 0

i = 1
while True:
    for p in range(1, plimit + 1):
        n = i**p
        if len(str(n)) == p:
            print i, p, n, len(str(n))
            rv += 1

        if len(str(n)) > p:
            #i = 10
            break
    i += 1
    #print i
    if i > 100000:
        break
print rv, timer() - t_start

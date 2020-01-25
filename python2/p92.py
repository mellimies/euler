# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=92
# Square digit chains

# How many starting numbers below ten million will arrive at 89?

from timeit import default_timer as timer
from python2.euler_tools import int2nums

t_start = timer()

limit = 10*10**6
nums = xrange(1, limit)
#nums = xrange(1, 100)
#nums = xrange(10*10**6, 1, -1)

rv = 0

powers = map(lambda x: x**2, range(0, 10))
print powers
#cache = [0] * (limit * 10)
cache = [0] * (limit * 1)
print "Empty cache created in ", timer() - t_start
#print "CACHE", len(cache), cache
for start_num in nums:
    t_num_start = timer()
    n = start_num
    #print "N", n
    num_list = [n]
    while True:
        if n > limit:
            cache.extend([0] * (n - limit))
            #print "CACHE NOW LONG", len(cache)
        if cache[n-1]:
            #print "CACHE", n, num_list, cache[n-1], cache

            for k in num_list:
                cache[k-1] = cache[n-1]

        #n = sum(map(lambda x: powers[int(x)], list(str(n))))
        n = sum(map(lambda x: powers[x], int2nums(n)))

        #print n, num_list
        if n in [1,89]:
            #if n == 1:
            #print "LOPPU", num_list, n, cache
            for k in num_list:
                cache[k-1] = n
            if n == 89:
                rv += 1
            #print "LOPPU", num_list, n, cache

            break
        #print num_list
        num_list.append(n)

    #print "N %d in " % start_num, timer() - t_num_start

print rv, len(filter(lambda x: x==1, cache)), len(filter(lambda x: x==89, cache)), timer() - t_start

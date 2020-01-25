# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=50
# Consecutive prime sum

# Which prime, below one-million, can be written as the sum of the most consecutive primes?

from timeit import default_timer as timer
from python2.euler_tools import is_prime, get_primes_up_to

t_start = timer()

ulimit = 1000000

#primes = [2,3]
#psum = sum(primes)
#n = primes[-1]
#while psum <= ulimit: # build list of primes until at limit
#    n += 2
#    if is_prime(n):
#        psum += n
#        primes.append(n)

primes = get_primes_up_to(ulimit / 10)
#print len(primes), primes

pmap = {}
num_primes = len(primes)
#t = [None] * num_primes

last_max = 0
rv = 0
i_start = -1
try:
    while i_start < num_primes - 2:
        i_start += 1
        print "MOI", i_start, num_primes
        #print "MOI", i_start, num_primes
        for i in range(num_primes):
            s = sum(primes[i_start:i])

            if is_prime(s) and s < ulimit:
                l = len(primes[i_start:i])
                if l > last_max:
                    last_max = l
                    rv = s
                    print s, len(primes[i_start:i]), last_max

            #if s >= ulimit:
            #    raise

except:
    pass
            #        pmap[s] = primes[i:]
print rv, timer() - t_start

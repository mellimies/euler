# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=104
# Pandigital Fibonacci ends

# Given that Fk is the first Fibonacci number for which the first nine digits AND the last nine digits are 1-9 pandigital, find k.
# k=329468 is correct answer

from timeit import default_timer as timer
from python2.euler_tools import *

t_start = timer()

rv=0

tset = set(range(1,10))
def int_to_list(n):
    l = []
    while n != 0:
        l = [n % 10] + l
        n = n // 10
    return l


def is_pandigital(n): # 362880 = 9!
    #print sorted(str(n))
    #return ''.join(sorted(str(n))) == "123456789"
    #num_list = int_to_list(n)
    #if 362880 == reduce(lambda x,y: x*y, num_list) and tset == set(num_list):
    #    return True
    #return False

    #return [1,2,3,4,5,6,7,8,9] == sorted(int_to_list(n))

    n = str(n)
    #print set(list(n))
    return not '0' in n and 9 == len(set(n))

#i = 299694 - 1
i = 0
low, high = 1,1
while True:
    i += 1
    if i < 2:
        low, high=1,1
    else:
        low, high = high, low+high
    f = low

    count = 0

    end = f % 10**9

    if not is_pandigital(end):
        continue
    count += 1
    print "END", i

    first_9 = first_n_digits(f, 9)
    #print first_9

    if not is_pandigital(first_9):
        continue

    count += 1
    print "START", i, first_9



    #
    #
    if count == 2:
        rv = i
        break

print rv, timer() - t_start
# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=88
# Product-sum numbers

# What is the sum of all the minimal product-sum numbers for 2≤k≤12000?

from timeit import default_timer as timer
import itertools

t_start = timer()


def m_sum(up_to):
    min_sum = None

    nums = range(1,up_to+1)
    print nums

    l = [nums] * len(nums)
    #print nums, l

    s = set()
#    for r in itertools.product(*l):
#    for r in itertools.product(nums, repeat=len(nums)):
#        s.add(tuple(sorted(r)))

#    for r in s:
    for r in itertools.product(nums, repeat=len(nums)):

        print "D", r
        sum_r = sum(r)
        num_sum = sum(r)
        if num_sum == reduce(lambda x,y: x*y, r):
            if num_sum > 1 and (not min_sum or sum_r < min_sum):
                #print "JEE", r, sum(r)

                min_sum = sum_r

    return min_sum

rv=0

sums = set()

#print m_sum(2)

i = 0
while True:
    i += 1
    v = m_sum(i)
    if v:
        sums.add(v)
    if i > 7:
        break

print sums, timer() - t_start
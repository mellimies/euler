# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=9
# Special Pythagorean triplet

problem = """
A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

a2 + b2 = c2
For example, 32 + 42 = 9 + 16 = 25 = 52.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.
"""

from timeit import default_timer as timer
t_start = timer()

a,b,c,target = 3,4,5,12
a,b,c,target = 1,2,997,1000

def get_numbers(a,b,c,target, loop):

    b += 1
    c -= 1
    if b >= c:

        loop += 1
        a = loop
        b = a + 1
        c = target - a - b

    assert a+b+c == target, "a+b+c must match target %s" % target

    return (a,b,c,loop)

rv = None
loop = 1
while rv is None:
    aa, bb, cc = a ** 2, b ** 2, c ** 2

    if aa+bb == cc:
        #rv = a,b,c
        break

    a,b,c,loop = get_numbers(a,b,c,target,loop)

rv = a*b*c # 31875000
print rv, timer() - t_start
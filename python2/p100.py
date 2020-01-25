# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=100
# Arranged probability

# By finding the first arrangement to contain over 10**12 = 1,000,000,000,000 discs in total, determine the number of
# blue discs that the box would contain.

from timeit import default_timer as timer
from sympy import *

t_start = timer()

def p(b, r): # check two blue discs
    if b+r < 10**12:
        pass
        #return -1

    t1 = Rational(b) / Rational(b+r)
    t2 = Rational(b-1) / Rational(b+r-1)
    return float(t1 * t2)

import math

def r(b):
    top = ( 1-2*b + math.sqrt( (2*b-1)**2 - 4*(b*(1-b)) ) )
    res = top / 2
    return res

print p(15, 6)
print p(85, 35)

blue = int(7.07106*10**11)
#blue = int(7*10**11)

while True:
    blue += 1
    red = r(blue)
    if int(red) == red:
        red = int(red)
        print "KYL", blue, red, blue+red
        if blue+red > 10**12:
            rv = blue
            print "JEE", p(blue, int(red)), blue, int(red), blue+red
            break

print rv, timer() - t_start
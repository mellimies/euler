# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=102
# Triangle containment

# find the number of triangles for which the interior contains the origin.

from timeit import default_timer as timer
from euler_tools import *
t_start = timer()

with open("p102_triangles.txt") as f:
    lines = f.read().splitlines()

rv=0

o = (0,0)
for l in lines:
    #print l
    r = map(int, l.split(","))
    p1, p2, p3 = (r[0], r[1]), (r[2], r[3]), (r[4], r[5])

    if point_in_triangle(o, p1, p2, p3):
        rv += 1

print rv, timer() - t_start